package net.dctime.learnmodding.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.handlers.IScreenHandler;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.recipe.GemInfusingStationRecipe;
import net.dctime.learnmodding.recipe.ModRecipes;
import net.dctime.learnmodding.screen.GemInfuserScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@JeiPlugin
public class JEILearnModdingModPlugin implements IModPlugin
{
    public static RecipeType<GemInfusingStationRecipe> GEM_INFUSION_TYPE =
            new RecipeType<>(GemInfusingStationCategory.UID, GemInfusingStationRecipe.class);

    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation(LearnModdingMod.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {

        registration.addRecipeCategories(new GemInfusingStationCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<GemInfusingStationRecipe> recipes = recipeManager.getAllRecipesFor(GemInfusingStationRecipe.Type.INSTANCE);
        registration.addRecipes(GEM_INFUSION_TYPE, recipes);
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registration)
    {
        IModPlugin.super.registerIngredients(registration);
    }
}
