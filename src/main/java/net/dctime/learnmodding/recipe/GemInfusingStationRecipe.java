package net.dctime.learnmodding.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.NonNullLazy;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

public class GemInfusingStationRecipe implements Recipe<SimpleContainer>
{
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public GemInfusingStationRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems)
    {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return recipeItems;
    }

    // does the recipe matches? if yes, it will return true, else false
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel)
    {
        // The speaker doesn't know either
        if (pLevel.isClientSide())
        {
            return false;
        }

        // if the input itemStack matches the recipe's one, return true
        return recipeItems.get(0).test(pContainer.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer)
    {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight)
    {
        return true;
    }

    @Override
    public ItemStack getResultItem()
    {
        return output.copy();
    }

    @Override
    public ResourceLocation getId()
    {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType()
    {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<GemInfusingStationRecipe>
    {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "gem_infusing"; // same as the serializers
    }

    public static class Serializer implements RecipeSerializer<GemInfusingStationRecipe>
    {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(LearnModdingMod.MODID, "gem_infusing"); // must match the files
        @Override
        public GemInfusingStationRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe)
        {
            // read json file and find "output" and put the thing written in the json to the output variable
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            // read json file and find "ingredients" then put them to the ingredients variable
            JsonArray ingredents = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            // make the variable for return
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            // read ingredients and put it into the inputs
            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromJson(ingredents.get(i)));
            }
            // returns a recipe
            return new GemInfusingStationRecipe(pRecipeId, output, inputs);
        }

        @Override
        public @Nullable GemInfusingStationRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer)
        {
            ItemStack output = pBuffer.readItem();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < ingredients.size(); i++)
            {
                ingredients.set(i, Ingredient.fromNetwork(pBuffer));
            }
            // get output
            return new GemInfusingStationRecipe(pRecipeId, output, ingredients);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, GemInfusingStationRecipe pRecipe)
        {
            pBuffer.writeItemStack(pRecipe.getResultItem(), false);
            pBuffer.writeInt(pRecipe.recipeItems.size());
            for (int i = 0; i < pRecipe.recipeItems.size(); i++)
            {
                pRecipe.recipeItems.get(i).toNetwork(pBuffer);
            }

        }
    }
}
