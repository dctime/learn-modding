/*
package net.dctime.learnmodding.world.feature;

import com.google.common.base.Suppliers;
import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;


public class ModConfiguredFeatures
{
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, LearnModdingMod.MODID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> ZIRCON_ORES =
            CONFIGURED_FEATURES.register("zircon_ore_configured_feature",
                    () -> new ConfiguredFeature<>(Feature.ORE,
                            new OreConfiguration(new BlockMatchTest(Blocks.STONE), ModBlocks.ZIRCON_ORE.get().defaultBlockState(),
                                    5, 0.1f)));

    public static void register(IEventBus eventBus)
    {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
*/
