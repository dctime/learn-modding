package net.dctime.learnmodding.recipe;

import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, LearnModdingMod.MODID);

    // recipe must match the type: gem_infusing
    // must match the GemInfusingStationRecipes' Type ID and Serializers
    public static final RegistryObject<RecipeSerializer<GemInfusingStationRecipe>> GEM_INFUSER_SERIALIZER =
            SERIALIZERS.register("gem_infusing", () -> GemInfusingStationRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
    }
}
