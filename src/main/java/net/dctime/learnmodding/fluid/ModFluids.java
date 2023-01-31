package net.dctime.learnmodding.fluid;

import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.block.ModBlocks;
import net.dctime.learnmodding.item.ModItems;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids
{
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, LearnModdingMod.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_SOAP_WATER = FLUIDS.register("source_soap_water",
            () -> new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(
                    () -> {return ModFluidTypes.SOAP_WATER_FLUID_TYPE.get();},
                    () -> {return ModFluids.SOURCE_SOAP_WATER.get();},
                    () -> {return ModFluids.FLOWING_SOAP_WATER.get();}
            )
                    .block(() -> (LiquidBlock) ModBlocks.SOAP_WATER_BLOCK.get())
                    .bucket(() -> ModItems.SOAP_WATER_BUCKET.get())));

    public static final RegistryObject<FlowingFluid> FLOWING_SOAP_WATER = FLUIDS.register("flowing_soap_water",
            () -> new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(
                    ModFluidTypes.SOAP_WATER_FLUID_TYPE,
                    ModFluids.SOURCE_SOAP_WATER,
                    ModFluids.FLOWING_SOAP_WATER
            )
                    .block(() -> (LiquidBlock) ModBlocks.SOAP_WATER_BLOCK.get())
                    .bucket(ModItems.SOAP_WATER_BUCKET)));

    public static void register(IEventBus eventBus)
    {
        FLUIDS.register(eventBus);
    }

}
