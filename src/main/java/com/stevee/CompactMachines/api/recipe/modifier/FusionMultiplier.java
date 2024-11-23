package com.stevee.CompactMachines.api.recipe.modifier;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.logic.OCParams;
import com.gregtechceu.gtceu.api.recipe.logic.OCResult;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FusionMultiplier implements RecipeModifier {
    @Override
    public @Nullable GTRecipe apply(MetaMachine machine, @NotNull GTRecipe recipe, @NotNull OCParams params, @NotNull OCResult result) {
        recipe = recipe.copy();

        recipe.duration = recipe.duration * 5;

        RecipeHelper.getInputContents(recipe, FluidRecipeCapability.CAP).forEach(fluidIngredient ->
                fluidIngredient.setAmount(fluidIngredient.getAmount() * 16)
        );

        RecipeHelper.getOutputContents(recipe, FluidRecipeCapability.CAP).forEach(fluidIngredient ->
                fluidIngredient.setAmount(fluidIngredient.getAmount() * 16)
        );
        return recipe;
    }
}
