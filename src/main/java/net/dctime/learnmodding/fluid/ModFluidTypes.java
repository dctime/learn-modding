package net.dctime.learnmodding.fluid;

import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

import java.util.Properties;

public class ModFluidTypes
{
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, LearnModdingMod.MODID);

    public static RegistryObject<FluidType> SOAP_WATER_FLUID_TYPE = FLUID_TYPES.register("soap_water_fluid",
            () -> new BaseFluidType(
                    new ResourceLocation("block/water_still"),
                    new ResourceLocation("block/water_flow"),
                    new ResourceLocation("misc/in_soap_water"),
                    0xFFCF96CA,
                    new Vector3f(224 / 255f, 56 / 255f, 208 / 255f),
                    FluidType.Properties.create().canSwim(false)
            ));

    public static void register(IEventBus eventBus)
    {
        FLUID_TYPES.register(eventBus);
    }

}
