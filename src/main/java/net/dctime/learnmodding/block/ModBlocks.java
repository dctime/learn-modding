package net.dctime.learnmodding.block;

import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.block.custom.JumpyBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;
import java.rmi.registry.Registry;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LearnModdingMod.MODID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LearnModdingMod.MODID);

    public static final RegistryObject<Block> ZIRCON_BLOCK = BLOCKS.register("zircon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.5f).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> ZIRCON_BLOCK_ITEM = BLOCK_ITEMS.register("zircon_block",
            () -> new BlockItem(ZIRCON_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Block> ZIRCON_ORE = BLOCKS.register("zircon_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5f).requiresCorrectToolForDrops(), UniformInt.of(10, 12)));
    public static final RegistryObject<Item> ZIRCON_ORE_ITEM = BLOCK_ITEMS.register("zircon_ore",
            () -> new BlockItem(ZIRCON_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Block> NETHERRACK_ZIRCON_ORE = BLOCKS.register("netherrack_zircon_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.3f).requiresCorrectToolForDrops().sound(SoundType.NETHERRACK), UniformInt.of(10, 12)));
    public static final RegistryObject<Item> NETHERRACK_ZIRCON_ORE_ITEM = BLOCK_ITEMS.register("netherrack_zircon_ore",
            () -> new BlockItem(NETHERRACK_ZIRCON_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE = BLOCKS.register("deepslate_zircon_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.6f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE), UniformInt.of(10, 12)));
    public static final RegistryObject<Item> DEEPSLATE_ZIRCON_ORE_ITEM = BLOCK_ITEMS.register("deepslate_zircon_ore",
            () -> new BlockItem(DEEPSLATE_ZIRCON_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Block> ENDSTONE_ZIRCON_ORE = BLOCKS.register("endstone_zircon_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.6f).requiresCorrectToolForDrops().sound(SoundType.STONE), UniformInt.of(10, 12)));
    public static final RegistryObject<Item> ENDSTONE_ZIRCON_ORE_ITEM = BLOCK_ITEMS.register("endstone_zircon_ore",
            () -> new BlockItem(ENDSTONE_ZIRCON_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Block> JUMPY_BLOCK = BLOCKS.register("jumpy_block",
            () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5f).sound(SoundType.STONE)));
    public static final RegistryObject<Item> JUMPY_BLOCK_ITEM = BLOCK_ITEMS.register("jumpy_block",
            () -> new BlockItem(JUMPY_BLOCK.get(), new Item.Properties()));




    public static void registerBLocksInModBlocksClass(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }


}
