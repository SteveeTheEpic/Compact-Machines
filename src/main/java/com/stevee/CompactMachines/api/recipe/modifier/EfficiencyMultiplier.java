package com.stevee.CompactMachines.api.recipe.modifier;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.stevee.CompactMachines.api.machine.multiblock.EfficiencyFactoryMachine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class EfficiencyMultiplier implements RecipeModifier {
    @Override
    public @NotNull ModifierFunction getModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof EfficiencyFactoryMachine efficiencyFactoryMachine)) return ModifierFunction.IDENTITY;
        int eff = efficiencyFactoryMachine.getEfficiency();
        return ModifierFunction.builder()
                .durationModifier(ContentModifier.multiplier(Math.max(eff/25, 1)))
                .inputModifier(ContentModifier.multiplier(Math.max(25/eff, 1)))
                .outputModifier(ContentModifier.multiplier(Math.max(eff/25, 1)))
                .build();
    }

    @Override
    public @Nullable GTRecipe applyModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {

        if (!(machine instanceof EfficiencyFactoryMachine efficiencyFactoryMachine)) return recipe;
        int eff = efficiencyFactoryMachine.getEfficiency();

        return ModifierFunction.builder()
                .durationModifier(ContentModifier.multiplier(Math.max(eff/25, 1)))
                .inputModifier(ContentModifier.multiplier(Math.max(25/eff, 1)))
                .outputModifier(ContentModifier.multiplier(Math.max(eff/25, 1)))
                .build().apply(recipe);
        //return recipe;
    }
}
