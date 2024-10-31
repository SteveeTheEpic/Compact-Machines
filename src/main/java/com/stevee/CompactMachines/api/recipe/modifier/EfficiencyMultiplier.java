package com.stevee.CompactMachines.api.recipe.modifier;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.logic.OCParams;
import com.gregtechceu.gtceu.api.recipe.logic.OCResult;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.stevee.CompactMachines.api.machine.multiblock.EfficiencyFactoryMachine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class EfficiencyMultiplier implements RecipeModifier {

    @Override
    public @Nullable GTRecipe apply(MetaMachine machine, @NotNull GTRecipe recipe, @NotNull OCParams params, @NotNull OCResult result) {

        if (!(machine instanceof EfficiencyFactoryMachine efficiencyFactoryMachine)) return recipe;

        recipe = recipe.copy();
        recipe.duration *= efficiencyFactoryMachine.getEfficiency();
        recipe.duration /= 100;

        RecipeHelper.getInputContents(recipe, ItemRecipeCapability.CAP).forEach(ingredient -> {
            Arrays.stream(ingredient.getItems()).iterator().forEachRemaining((itemStack) -> {
                itemStack.setCount(itemStack.getCount() * Math.max((50 / efficiencyFactoryMachine.getEfficiency()), 1));
            });
        });

        RecipeHelper.getOutputContents(recipe, ItemRecipeCapability.CAP).forEach(ingredient -> {
            Arrays.stream(ingredient.getItems()).iterator().forEachRemaining(itemStack -> {
                itemStack.setCount(itemStack.getCount() * Math.max(((efficiencyFactoryMachine.getEfficiency()) / 25), 1));
            });
        });

        if (recipe.duration <= 0) {
            recipe.duration = 1;
        }

        return recipe;
    }
}
