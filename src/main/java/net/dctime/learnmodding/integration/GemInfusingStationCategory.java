package net.dctime.learnmodding.integration;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableBuilder;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.block.ModBlocks;
import net.dctime.learnmodding.item.ModItems;
import net.dctime.learnmodding.recipe.GemInfusingStationRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class GemInfusingStationCategory implements IRecipeCategory<GemInfusingStationRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(LearnModdingMod.MODID, "gem_infusing");
    private ResourceLocation backgroundAsset = new ResourceLocation(LearnModdingMod.MODID, "textures/gui/gem_infusing_station_gui.png");
    private RecipeType<GemInfusingStationRecipe> recipeType;
    private Component title;
    private IDrawable background;
    private IDrawable icon;

    public GemInfusingStationCategory(IGuiHelper guiHelper)
    {
        this.background = guiHelper.createDrawable(backgroundAsset, 0 , 0, 176, 85);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ModBlocks.GEM_INFUSER_BLOCK_ITEM.get().getDefaultInstance());
    }

    @Override
    public RecipeType<GemInfusingStationRecipe> getRecipeType()
    {
        return JEILearnModdingModPlugin.GEM_INFUSION_TYPE;
    }

    @Override
    public Component getTitle()
    {
        return Component.translatable("learnmodding:jei_gem_infusing_title");
    }

    @Override
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    public IDrawable getIcon()
    {
        return this.icon;
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GemInfusingStationRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}
