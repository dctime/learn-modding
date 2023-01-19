package net.dctime.learnmodding.painting;

import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintingVariant
{
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIENT = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS,
            LearnModdingMod.MODID);

    public static final RegistryObject<PaintingVariant> PLANT = PAINTING_VARIENT.register("plant",
            () -> new PaintingVariant(16, 16));

    public static final RegistryObject<PaintingVariant> SUNSET = PAINTING_VARIENT.register("sunset",
            () -> new PaintingVariant(32, 16));

    public static final RegistryObject<PaintingVariant> WANDERER = PAINTING_VARIENT.register("wanderer",
            () -> new PaintingVariant(16, 32));
    public static void register(IEventBus eventBus)
    {
        PAINTING_VARIENT.register(eventBus);
    }

}
