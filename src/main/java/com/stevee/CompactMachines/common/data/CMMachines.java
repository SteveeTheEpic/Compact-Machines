package com.stevee.CompactMachines.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.common.data.GTCompassSections;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.part.DualHatchPartMachine;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.stevee.CompactMachines.CompactMachinesMod;
import com.stevee.CompactMachines.api.machine.multiblock.PCBFactoryMachine;
import com.stevee.CompactMachines.api.recipe.modifier.EfficiencyMultiplier;
import com.stevee.CompactMachines.common.machine.multiblock.part.LowDualHatchPartMachine;
import net.minecraft.network.chat.Component;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.stevee.CompactMachines.api.registries.Registrate.REGISTRATE;
import static com.stevee.CompactMachines.common.data.CMBlocks.COMPACT_CHEMICAL_REACTOR_CASING;
import static com.stevee.CompactMachines.common.data.CMBlocks.PCB_FACTORY_CASING;

public class CMMachines {

    public static final MachineDefinition COMPACT_CHEMICAL_REACTOR = REGISTRATE
            .multiblock("compact_chemical_reactor", WorkableElectricMultiblockMachine::new)
            .langValue("Compact Chemical Reactor")
            .tooltips(GTMachines.defaultEnvironmentRequirement())
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
            .recipeModifiers(GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK_SUBTICK))
            .appearanceBlock(COMPACT_CHEMICAL_REACTOR_CASING)
            .pattern(definition -> {
                var casing = blocks(COMPACT_CHEMICAL_REACTOR_CASING.get()).setMinGlobalLimited(7);
                var abilities = Predicates.autoAbilities(definition.getRecipeTypes())
                        .or(Predicates.autoAbilities(true, false, true));
                return FactoryBlockPattern.start()
                        .aisle("XXX", "XCX", "XXX")
                        .aisle("XCX", "CPC", "XCX")
                        .aisle("XXX", "XSX", "XXX")
                        .where('S', Predicates.controller(blocks(definition.getBlock())))
                        .where('X', casing.or(abilities))
                        .where('P', blocks(CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
                        .where('C', blocks(SUPERCONDUCTING_COIL.get()).setExactLimit(1)
                                .or(abilities)
                                .or(casing))
                        .build();
            })
            .workableCasingRenderer(CompactMachinesMod.id("block/casings/solid/compact_chemical_reactor_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .compassSections(GTCompassSections.TIER[LuV])
            .compassNodeSelf()
            .register();

    public static final MachineDefinition PCB_FACTORY = REGISTRATE
            .multiblock("pcb_factory", PCBFactoryMachine::new)
            .langValue("PCB Factory V1.01a")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CMRecipeTypes.PCB_Factory)
            .recipeModifiers(GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
                    new EfficiencyMultiplier())
            .appearanceBlock(PCB_FACTORY_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("DDD", "DDD", "DDD")
                    .aisle("DDD", "D#D", "DDD")
                    .aisle("DDD", "DCD", "DDD")
                    .where("D", autoAbilities(definition.getRecipeTypes())
                            .or(autoAbilities(true, false, true))
                            .or(blocks(PCB_FACTORY_CASING.get())))
                    .where("C", controller(blocks(definition.getBlock())))
                    .where("#", air())
                    .build())
            .workableCasingRenderer(CompactMachinesMod.id("block/casings/solid/pcb_factory_casing"), GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static final MachineDefinition[] LOW_DUAL_IMPORT_HATCH = registerTieredMachines("low_dual_input_hatch",
            (holder, tier) -> new LowDualHatchPartMachine(holder, tier, IO.IN),
            (tier, builder) -> builder
                    .langValue("%s Low Dual Input Hatch".formatted(VNF[tier]))
                    .rotationState(RotationState.ALL)
                    .abilities(PartAbility.IMPORT_FLUIDS, PartAbility.IMPORT_ITEMS)
                    .overlayTieredHullRenderer("dual_hatch.import")
                    .tooltips(
                            Component.translatable("gtceu.machine.dual_hatch.import.tooltip"),
                            Component.translatable(
                                    "gtceu.universal.tooltip.item_storage_capacity",
                                    tier,
                            Component.translatable(
                                    "gtceu.universal.tooltip.fluid_storage_capacity_mult",
                                    tier,
                                    DualHatchPartMachine.getTankCapacity(
                                            DualHatchPartMachine.INITIAL_TANK_CAPACITY, tier))),
                            Component.translatable("gtceu.universal.enabled"))
                    .compassNode("low_dual_hatch")
                    .register(), GTValues.tiersBetween(HV, IV));


    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = GTRegistration.REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static void init() {}
}
