package com.stevee.CompactMachines.common.data.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.GTRegistry;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.MaintenanceHatchPartRenderer;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.machine.multiblock.part.CleaningMaintenanceHatchPartMachine;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.machinezoo.noexception.throwing.ThrowingConsumer;
import com.stevee.CompactMachines.CompactMachinesMod;
import com.stevee.CompactMachines.api.machine.multiblock.EfficiencyFactoryMachine;
import com.stevee.CompactMachines.api.recipe.modifier.EfficiencyMultiplier;
import com.stevee.CompactMachines.common.data.CMRecipeTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.LuV;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.stevee.CompactMachines.api.registries.Registrate.REGISTRATE;
import static com.stevee.CompactMachines.common.data.CMBlocks.*;

public class CMMachines {

    public static final MachineDefinition COMPACT_CHEMICAL_REACTOR = REGISTRATE
            .multiblock("compact_chemical_reactor", WorkableElectricMultiblockMachine::new)
            .langValue("Compact Chemical Reactor")
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
            .register();

    public static final MachineDefinition PCB_FACTORY = REGISTRATE
            .multiblock("pcb_factory", EfficiencyFactoryMachine::new)
            .langValue("PCB Factory V1.01a")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(CMRecipeTypes.PCB_Factory)
            .recipeModifiers(GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
                    new EfficiencyMultiplier())
            .appearanceBlock(PCB_FACTORY_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("DDD", "DDD", "DDD")
                    .aisle("DDD", "D#D", "DDD")
                    .aisle("DDD", "DCD", "DDD")
                    .where("D", autoAbilities(definition.getRecipeTypes())
                            .or(autoAbilities(true, false, false))
                            .or(blocks(PCB_FACTORY_CASING.get())))
                    .where("C", controller(blocks(definition.getBlock())))
                    .where("#", air())
                    .build())
            .workableCasingRenderer(CompactMachinesMod.id("block/casings/solid/pcb_factory_casing"), GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static final MachineDefinition CIRCUIT_FACTORY = REGISTRATE
            .multiblock("circuit_factory", EfficiencyFactoryMachine::new)
            .langValue("Circuit Factory V1.01a")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(CMRecipeTypes.Circuit_Factory)
            .recipeModifiers(GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
                    new EfficiencyMultiplier())
            .appearanceBlock(CIRCUIT_FACTORY_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("DDD", "DDD", "DDD")
                    .aisle("DDD", "D#D", "DDD")
                    .aisle("DDD", "DCD", "DDD")
                    .where("D", autoAbilities(definition.getRecipeTypes())
                            .or(autoAbilities(true, false, false))
                            .or(blocks(CIRCUIT_FACTORY_CASING.get())))
                    .where("C", controller(blocks(definition.getBlock())))
                    .where("#", air())
                    .build())
            .workableCasingRenderer(CompactMachinesMod.id("block/casings/solid/circuit_factory_casing"), GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static final MachineDefinition EXTENDED_ME_PATTERN_BUFFER = REGISTRATE
            .machine("extended_me_pattern_buffer", ExtendedMePatternBuffer::new)
            .tier(LuV)
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.IMPORT_ITEMS, PartAbility.IMPORT_FLUIDS, PartAbility.EXPORT_FLUIDS,
                    PartAbility.EXPORT_ITEMS)
            .rotationState(RotationState.ALL)
            .overlayTieredHullRenderer("extended_me_pattern_buffer_hatch")
            .langValue("Extended ME Pattern Buffer")
            .tooltips(
                    Component.translatable("block.gtceu.pattern_buffer.desc.0"),
                    Component.translatable("block.gtceu.pattern_buffer.desc.1"),
                    Component.translatable("block.gtceu.pattern_buffer.desc.2"),
                    Component.translatable("gtceu.universal.enabled"))
            .register();

    public static final MachineDefinition STERILE_MAINTENANCE_HATCH = REGISTRATE
            .machine("sterile_maintenance_hatch", holder -> new CleaningMaintenanceHatchPartMachine(holder, CleanroomType.STERILE_CLEANROOM))
            .tier(LuV)
            .langValue("Sterile Maintenance Hatch")
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.MAINTENANCE)
            .tooltips(Component.translatable("gtceu.universal.disabled"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.0"),
                    Component.translatable("gtceu.machine.maintenance_hatch_cleanroom_auto.tooltip.1"))
            .tooltipBuilder((stack, tooltips) -> {
                tooltips.add(Component.literal("  ").append(Component
                        .translatable(CleanroomType.STERILE_CLEANROOM.getTranslationKey()).withStyle(ChatFormatting.GREEN)));
            })
            .renderer(() -> new MaintenanceHatchPartRenderer(6, CompactMachinesMod.id("block/machine/part/sterile_maintenance_hatch")))
            .register();

    /*public static final MachineDefinition[] LOW_DUAL_IMPORT_HATCH = registerTieredMachines("low_dual_input_hatch",
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
                    .register(), GTValues.tiersBetween(HV, IV));*/


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
