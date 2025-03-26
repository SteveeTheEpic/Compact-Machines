package com.stevee.CompactMachines.api.recipe.modifier;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FusionMultiplier implements RecipeModifier {
    @Override
    public @NotNull ModifierFunction getModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        return ModifierFunction.builder()
                .durationModifier(ContentModifier.multiplier(5))
                .inputModifier(ContentModifier.multiplier(16))
                .outputModifier(ContentModifier.multiplier(16))
                .build();
    }

    @Override
    public @Nullable GTRecipe applyModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        return ModifierFunction.builder()
                .durationModifier(ContentModifier.multiplier(5))
                .inputModifier(ContentModifier.multiplier(16))
                .outputModifier(ContentModifier.multiplier(16))
                .build().apply(recipe);
    }
}
