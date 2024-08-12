package com.pizzaduddes.neopizzasmod.datagen;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import com.pizzaduddes.neopizzasmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> TANZANITE_SMELTABLES = List.of(ModItems.RAW_TANZANITE,
                ModBlocks.TANZANITE_ORE, ModBlocks.DEEPSLATE_TANZANITE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TANZANITE_BLOCK.get())
                .pattern("##")
                .pattern("##")
                .define('#', ModItems.TANZANITE.get())
                .unlockedBy("has_tanzanite", has(ModItems.TANZANITE.get())).save(recipeOutput, "neopizzasmod:tanzanite_block_1");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TANZANITE, 4)
                .requires(ModBlocks.TANZANITE_BLOCK.get())
                .unlockedBy("has_tanzanite", has(ModItems.TANZANITE.get())).save(recipeOutput, "neopizzasmod:tanzanite_1");


        oreSmelting(recipeOutput, TANZANITE_SMELTABLES, RecipeCategory.MISC, ModItems.TANZANITE.get(), 0.25f, 200, "tanzanite");
        oreBlasting(recipeOutput, TANZANITE_SMELTABLES, RecipeCategory.MISC, ModItems.TANZANITE.get(), 0.25f, 100, "tanzanite");

    }
    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, NeoPizzasMod.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));

        }
    }
}
