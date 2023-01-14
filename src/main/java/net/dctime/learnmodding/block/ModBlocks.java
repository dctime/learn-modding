package net.dctime.learnmodding.block;

import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LearnModdingMod.MODID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LearnModdingMod.MODID);

    public static final RegistryObject<Block> ZIRCON_BLOCK = BLOCKS.register("zircon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> ZIRCON_BLOCK_ITEM = BLOCK_ITEMS.register("zircon_block",
            () -> new BlockItem(ZIRCON_BLOCK.get(), new Item.Properties()));





    public static void registerBLocksInModBlocksClass(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }


}
