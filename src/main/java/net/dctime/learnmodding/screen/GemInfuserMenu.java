package net.dctime.learnmodding.screen;

import net.dctime.learnmodding.block.ModBlocks;
import net.dctime.learnmodding.block.entity.GemInfusingStationBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class GemInfuserMenu extends AbstractContainerMenu
{
    private final GemInfusingStationBlockEntity blockEntity;
    private final Level level;
    private final ContainerData containerData;

    public GemInfuserMenu(int id, Inventory inventory, FriendlyByteBuf buffer)
    {
        this(id, inventory, inventory.player.level.getBlockEntity(buffer.readBlockPos()), new SimpleContainerData(2));
    }

    public GemInfuserMenu(int id, Inventory inventory, BlockEntity blockEntity, ContainerData data)
    {
        super(ModMenuTypes.GEM_INFUSER_MENU.get(), id);
        checkContainerSize(inventory, 3);
        this.blockEntity = (GemInfusingStationBlockEntity) blockEntity;
        this.level = inventory.player.level;
        this.containerData = data;

        addPlayerHotbar(inventory);
        addPlayerInventory(inventory);

        // if ITEM_HANDLER_CAPABILITY, returns lazyItemHandler
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(
                (lazyItemHandler) ->
                {
                    // Client Side
                    this.addSlot(new SlotItemHandler(lazyItemHandler, 0, 12, 15));
                    this.addSlot(new SlotItemHandler(lazyItemHandler, 1, 86, 15));
                    this.addSlot(new SlotItemHandler(lazyItemHandler, 2, 86, 60));
                }

        );
        addDataSlots(data);
    }

    /* This thing is bruh*/
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!

    // When Shift Clicked..
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    // if block broken, player died
    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.GEM_INFUSER_BLOCK.get());
    }

    private void addPlayerInventory(Inventory playerInventory)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int l = 0; l < 9; l++)
            {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9,  8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory)
    {
        for (int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

    public float getProcessPercent()
    {
        return (float) this.containerData.get(0) / this.containerData.get(1);
    }
}
