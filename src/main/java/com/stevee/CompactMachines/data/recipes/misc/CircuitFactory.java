package com.stevee.CompactMachines.data.recipes.misc;

import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.data.recipe.CustomTags.*;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.stevee.CompactMachines.common.data.CMRecipeTypes.Circuit_Factory;

public class CircuitFactory {

    public static void init(Consumer<FinishedRecipe> provider) {

        Circuit_Factory.recipeBuilder("microprocessor")
                .inputItems(PLASTIC_CIRCUIT_BOARD, 1)
                .inputItems(CENTRAL_PROCESSING_UNIT, 1)
                .inputItems(RESISTORS, 2)
                .inputItems(CAPACITORS, 2)
                .inputItems(TRANSISTORS, 2)
                .inputFluids(SolderingAlloy.getFluid(36))
                .outputItems(PROCESSOR_MV, 2)
                .duration(200).EUt(VA[MV]).save(provider);

        Circuit_Factory.recipeBuilder("microprocessor_assembly")
                .inputItems(PLASTIC_CIRCUIT_BOARD, 1)
                .inputItems(PROCESSOR_MV, 1)
                .inputItems(INDUCTORS, 2)
                .inputItems(TRANSISTORS, 4)
                .inputItems(RANDOM_ACCESS_MEMORY, 2)
                .inputItems(wireFine, RedAlloy, 4)
                .inputFluids(SolderingAlloy.getFluid(72))
                .outputItems(PROCESSOR_ASSEMBLY_HV)
                .duration(200).EUt(VA[MV]).save(provider);

        Circuit_Factory.recipeBuilder("microprocessor_supercomputer")
                .inputItems(PLASTIC_CIRCUIT_BOARD, 1)
                .inputItems(PROCESSOR_ASSEMBLY_HV, 1)
                .inputItems(DIODES, 2)
                .inputItems(RANDOM_ACCESS_MEMORY, 2)
                .inputItems(wireFine, Gold, 8)
                .inputItems(bolt, BlueAlloy, 8)
                .inputFluids(SolderingAlloy.getFluid(144))
                .outputItems(WORKSTATION_EV, 1)
                .duration(200).EUt(VA[MV]).save(provider);

        Circuit_Factory.recipeBuilder("microprocessor_mainframe")
                .inputItems(frameGt, Aluminium, 1)
                .inputItems(WORKSTATION_EV, 1)
                .inputItems(ADVANCED_SMD_INDUCTOR, 1)
                .inputItems(ADVANCED_SMD_CAPACITOR, 2)
                .inputItems(RANDOM_ACCESS_MEMORY, 8)
                .inputItems(wireGtSingle, AnnealedCopper, 8)
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(MAINFRAME_IV, 1)
                .duration(200).EUt(VA[HV]).save(provider);

        Circuit_Factory.recipeBuilder("nano_processor")
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(NANO_CENTRAL_PROCESSING_UNIT)
                .inputItems(SMD_RESISTOR, 4)
                .inputItems(SMD_CAPACITOR, 4)
                .inputItems(SMD_TRANSISTOR, 4)
                .inputFluids(SolderingAlloy.getFluid(72))
                .outputItems(NANO_PROCESSOR_HV, 2)
                .duration(200).EUt(VA[HV]).save(provider);

        Circuit_Factory.recipeBuilder("nano_processor_assembly")
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(NANO_PROCESSOR_HV, 2)
                .inputItems(SMD_INDUCTOR, 2)
                .inputItems(SMD_CAPACITOR, 4)
                .inputItems(RANDOM_ACCESS_MEMORY, 4)
                .inputItems(wireFine, Electrum, 4)
                .inputFluids(SolderingAlloy.getFluid(144))
                .outputItems(NANO_PROCESSOR_ASSEMBLY_EV, 2)
                .duration(200).EUt(VA[HV]).save(provider);

        Circuit_Factory.recipeBuilder("nano_supercomputer")
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(NANO_PROCESSOR_ASSEMBLY_EV, 2)
                .inputItems(ADVANCED_SMD_DIODE, 2)
                .inputItems(NOR_MEMORY_CHIP, 2)
                .inputItems(RANDOM_ACCESS_MEMORY, 8)
                .inputItems(wireFine, Electrum, 8)
                .inputFluids(SolderingAlloy.getFluid(144))
                .outputItems(NANO_COMPUTER_IV)
                .duration(200).EUt(VA[HV]).save(provider);

        Circuit_Factory.recipeBuilder("nano_mainframe")
                .inputItems(frameGt, Aluminium, 2)
                .inputItems(NANO_COMPUTER_IV, 2)
                .inputItems(SMD_INDUCTOR, 8)
                .inputItems(SMD_CAPACITOR, 16)
                .inputItems(RANDOM_ACCESS_MEMORY, 8)
                .inputItems(wireGtSingle, AnnealedCopper, 16)
                .inputFluids(SolderingAlloy.getFluid(288))
                .outputItems(NANO_MAINFRAME_LuV)
                .duration(200).EUt(VA[HV]).save(provider);

        Circuit_Factory.recipeBuilder("quantum_processor")
                .inputItems(EXTREME_CIRCUIT_BOARD)
                .inputItems(QUBIT_CENTRAL_PROCESSING_UNIT)
                .inputItems(NANO_CENTRAL_PROCESSING_UNIT)
                .inputItems(ADVANCED_SMD_CAPACITOR, 2)
                .inputItems(ADVANCED_SMD_TRANSISTOR, 4)
                .inputItems(wireFine, Platinum, 6)
                .inputFluids(SolderingAlloy.getFluid(36))
                .outputItems(QUANTUM_PROCESSOR_EV)
                .duration(200).EUt(VA[HV]).save(provider);

        Circuit_Factory.recipeBuilder("quantum_processor_assembly")
                .inputItems(EXTREME_CIRCUIT_BOARD)
                .inputItems(QUANTUM_PROCESSOR_EV)
                .inputItems(ADVANCED_SMD_INDUCTOR)
                .inputItems(ADVANCED_SMD_CAPACITOR, 2)
                .inputItems(RANDOM_ACCESS_MEMORY, 2)
                .inputItems(wireFine, Platinum, 8)
                .inputFluids(SolderingAlloy.getFluid(72))
                .outputItems(QUANTUM_ASSEMBLY_IV)
                .duration(200).EUt(VA[HV]).save(provider);

        Circuit_Factory.recipeBuilder("quantum_supercomputer")
                .inputItems(EXTREME_CIRCUIT_BOARD)
                .inputItems(QUANTUM_ASSEMBLY_IV)
                .inputItems(ADVANCED_SMD_DIODE, 2)
                .inputItems(NOR_MEMORY_CHIP, 4)
                .inputItems(RANDOM_ACCESS_MEMORY, 16)
                .inputItems(wireFine, Platinum, 32)
                .outputItems(QUANTUM_COMPUTER_LuV)
                .duration(200).EUt(VA[HV]).save(provider);

    }

}
