package com.stevee.CompactMachines.data.recipes.misc;

import appeng.core.definitions.AEItems;
import com.gregtechceu.gtceu.common.data.machines.GTAEMachines;
import com.stevee.CompactMachines.common.data.machines.CMMachines;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.api.GTValues.LuV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Europium;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Lubricant;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;

public class Machines {
    public static void init(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder("extended_me_pattern_buffer_recipe")
                .inputItems(GTAEMachines.ME_PATTERN_BUFFER, 1)
                .inputItems(AEItems.CAPACITY_CARD.asItem(), 3)
                .inputItems(AEItems.SPEED_CARD.asItem(), 16)
                .inputItems(wireFine, Europium, 32)
                .inputItems(wireFine, Europium, 32)
                .inputFluids(Lubricant.getFluid(144 * 8 - 1))
                .outputItems(CMMachines.EXTENDED_ME_PATTERN_BUFFER, 1)
                .duration(600).EUt(VA[LuV]).save(provider);
    }
}
