/*
package net.dctime.learnmodding.world.feature;

import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures
{
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registries.PLACED_FEATURE, LearnModdingMod.MODID);

    public static final RegistryObject<PlacedFeature> ZIRCON_ORE_PLACED_FEATURE =
            PLACED_FEATURES.register("zircon_ore_placed",
                    () -> new PlacedFeature(ModConfiguredFeatures.ZIRCON_ORES.getHolder().get(),
                            List.of(
                                    CountPlacement.of(7),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(30))),
                                    BiomeFilter.biome()
                            )));

    public static void register(IEventBus eventBus)
    {
        PLACED_FEATURES.register(eventBus);
    }
}
*/
