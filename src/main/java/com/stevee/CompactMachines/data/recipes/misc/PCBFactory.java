package com.stevee.CompactMachines.data.recipes.misc;

import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.stevee.CompactMachines.common.data.CMRecipeTypes.PCB_Factory;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class PCBFactory {

    public static void init(Consumer<FinishedRecipe> provider) {
            PCB_Factory.recipeBuilder("resin_printed_circuit_board")
                .inputItems(plate, Wood, 1)
                .inputItems(foil, Copper, 1)
                .outputItems(BASIC_CIRCUIT_BOARD, 1)
                .duration(1000).EUt(VA[LuV]).save(provider);

            PCB_Factory.recipeBuilder("phenolic_printed_circuit_board")
                    .inputItems(dust, Wood, 1)
                    .inputItems(foil, Silver, 2)
                    .inputFluids(Glue.getFluid(25))
                    .outputItems(GOOD_CIRCUIT_BOARD, 1)
                    .duration(1000).EUt(VA[LuV]).save(provider);

            PCB_Factory.recipeBuilder("plastic_printed_circuit_board")
                    .inputItems(plate, PolyvinylChloride, 1)
                    .inputItems(foil, Copper, 1)
                    .inputFluids(SulfuricAcid.getFluid(125))
                    .inputFluids(Iron3Chloride.getFluid(25))
                    .outputItems(PLASTIC_CIRCUIT_BOARD)
                    .duration(1000).EUt(VA[LuV]).save(provider);

            PCB_Factory.recipeBuilder("epoxy_printed_circuit_board")
                    .inputItems(plate, Epoxy, 1)
                    .inputItems(foil, Copper, 4)
                    .inputItems(foil, Electrum, 4)
                    .inputFluids(SulfuricAcid.getFluid(75))
                    .inputFluids(Iron3Chloride.getFluid(125))
                    .outputItems(ADVANCED_CIRCUIT_BOARD, 1)
                    .duration(1000).EUt(VA[LuV]).save(provider);

            PCB_Factory.recipeBuilder("fiber_reinforced_printed_circuit_board")
                    .inputItems(plate, ReinforcedEpoxyResin, 1)
                    .inputItems(foil, AnnealedCopper, 8)
                    .inputFluids(SulfuricAcid.getFluid(50))
                    .inputFluids(Iron3Chloride.getFluid(250))
                    .outputItems(EXTREME_CIRCUIT_BOARD)
                    .duration(1000).EUt(VA[LuV]).save(provider);

            PCB_Factory.recipeBuilder("multi_layer_fiber_reinforced_printed_circuit_board")
                    .inputItems(plate, ReinforcedEpoxyResin, 1)
                    .inputItems(foil, Palladium, 2)
                    .inputItems(foil, Platinum, 2)
                    .inputFluids(SulfuricAcid.getFluid(25))
                    .inputFluids(Iron3Chloride.getFluid(500))
                    .outputItems(ELITE_CIRCUIT_BOARD)
                    .duration(1000).EUt(VA[LuV]).save(provider);

    }

}
