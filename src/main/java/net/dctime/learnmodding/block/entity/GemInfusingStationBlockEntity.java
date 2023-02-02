package net.dctime.learnmodding.block.entity;

import com.mojang.serialization.Decoder;
import net.dctime.learnmodding.block.custom.GemInfusingStationBlock;
import net.dctime.learnmodding.item.ModItems;
import net.dctime.learnmodding.screen.GemInfuserMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GemInfusingStationBlockEntity extends BlockEntity implements MenuProvider
{
    // make a inventory which has 3 slots in it
    private ItemStackHandler itemStackHandler = new ItemStackHandler(3)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
        }
    };
    // making some fields and store them into data
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    // make
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public GemInfusingStationBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        super(ModBlockEntities.GEM_INFUSER_BLOCK_ENTITY.get(), blockPos, blockState);
        this.data = new ContainerData()
        {
            @Override
            public int get(int index)
            {
                int value;
                switch (index)
                {
                    case 0:
                        value = progress;
                        break;
                    case 1:
                        value = maxProgress;
                        break;
                    default:
                        value = 0;
                }
                return value;
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0:
                        progress = value;
                    case 1:
                        maxProgress = value;
                }
            }

            @Override
            // how many indexes in the container
            public int getCount()
            {
                return 2;
            }
        };

    }

    public Component getDisplayName()
    {
        return Component.translatable("menu.learnmodding.gem_infuser_gui");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player)
    {
        return new GemInfuserMenu(id, inventory, this, this.data);
    }

    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    // when chunk being loaded
    @Override
    public void onLoad()
    {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    // when somehow the game decided to make its capabilities never work
    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    // when chunk unloaded and the chunk is being saved
    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        nbt.put("inventory", itemStackHandler.serializeNBT()); // serialize -> store into file
        super.saveAdditional(nbt);
    }

    // when chuck is being loaded and nbt is loading
    @Override
    public void load(CompoundTag nbt)
    {
        itemStackHandler.deserializeNBT(nbt); // deserailize -> file comes out
        super.load(nbt);
    }

    // called in the Block class
    public void dropEverythingWhenBreak()
    {
        SimpleContainer container = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++)
        {
            container.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.getBlockPos(), container);
    }


    public static void tick(Level level, BlockPos blockPos, BlockState blockState, GemInfusingStationBlockEntity entity)
    {
        // called in every tick
        if (!level.isClientSide() && validInputAndOutput(entity))
        {
            System.out.println(entity.progress);
            if (entity.progress < entity.maxProgress)
            {
                entity.progress++;
                setChanged(level, blockPos, blockState);
            }
            else
            {
                entity.progress = 0;
                entity.itemStackHandler.extractItem(1, 1, false);
                entity.itemStackHandler.setStackInSlot(2, new ItemStack(ModItems.ZIRCON.get(),
                        entity.itemStackHandler.getStackInSlot(2).getCount()+1));
                setChanged(level, blockPos, blockState);
            }
        }

    }

    private static boolean validInputAndOutput(GemInfusingStationBlockEntity blockEntity)
    {
        return blockEntity.itemStackHandler.getStackInSlot(1).getItem() == ModItems.RAW_ZIRCON.get() &&
        canPutThingsToSlot(blockEntity.itemStackHandler, ModItems.ZIRCON.get());
    }

    private static boolean canPutThingsToSlot(ItemStackHandler itemStackHandler, Item testItem)
    {
        return (
                itemStackHandler.getStackInSlot(2).getItem() == testItem &&
                        itemStackHandler.getStackInSlot(2).getCount() < itemStackHandler.getStackInSlot(2).getMaxStackSize()
        )
                || itemStackHandler.getStackInSlot(2).isEmpty();
    }
}
