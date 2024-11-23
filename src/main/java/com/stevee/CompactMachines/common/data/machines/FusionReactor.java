package com.stevee.CompactMachines.common.data.machines;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.stevee.CompactMachines.api.recipe.modifier.FusionMultiplier;
import net.minecraft.network.chat.Component;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.stevee.CompactMachines.api.registries.Registrate.REGISTRATE;

public class FusionReactor {

    public static final MachineDefinition compact_fusion_reactor = REGISTRATE
            .multiblock("compact_fusion_reactor", WorkableElectricMultiblockMachine::new)
            .langValue("Compact Fusion Reactor")
            .tooltips(Component.literal("This machine requires 100x more resources than the Fusion Reactor"))
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.FUSION_RECIPES)
            .recipeModifiers(new FusionMultiplier())
            .appearanceBlock(GTBlocks.FUSION_CASING_MK3)
            .pattern(definition -> {
                var abilities = autoAbilities(definition.getRecipeTypes());
                var glass = blocks(GTBlocks.FUSION_CASING_MK3.get()).or(blocks(GTBlocks.FUSION_GLASS.get()));
                var casing = blocks(GTBlocks.FUSION_CASING_MK3.get()).or(abilities);

                return FactoryBlockPattern.start()
                        .aisle("#################", "######CCCCC######", "######CGGGC######", "######CCCCC######", "#################")
                        .aisle("######CCCCC######", "####CC#####CC####", "####CC#####CC####", "####CC#####CC####", "######CCCCC######")
                        .aisle("####CCCGGGCCC####", "###C#########C###", "###C#########C###", "###C#########C###", "####CCCGGGCCC####")
                        .aisle("###CCCCCCCCCCC###", "##C###########C##", "##C###########C##", "##C###########C##", "###CCCCCCCCCCC###")
                        .aisle("##CCCC#####CCCC##", "#C####CCCCC####C#", "#C####CGMGC####C#", "#C####CCCCC####C#", "##CCCC#####CCCC##")
                        .aisle("##CCC#######CCC##", "#C###C#####C###C#", "#C###C#####C###C#", "#C###C#####C###C#", "##CCC#######CCC##")
                        .aisle("#CCC#########CCC#", "C###C#######C###C", "C###C#######C###C", "C###C#######C###C", "#CCC#########CCC#")
                        .aisle("#CGC#########CGC#", "C###C#######C###C", "G###G#######G###G", "C###C#######C###C", "#CGC#########CGC#")
                        .aisle("#CGC#########CGC#", "C###C#######C###C", "G###G#######G###G", "C###C#######C###C", "#CGC#########CGC#")
                        .aisle("#CGC#########CGC#", "C###C#######C###C", "G###G#######G###G", "C###C#######C###C", "#CGC#########CGC#")
                        .aisle("#CCC#########CCC#", "C###C#######C###C", "C###C#######C###C", "C###C#######C###C", "#CCC#########CCC#")
                        .aisle("##CCC#######CCC##", "#C###C#####C###C#", "#C###C#####C###C#", "#C###C#####C###C#", "##CCC#######CCC##")
                        .aisle("##CCCC#####CCCC##", "#C####CCCCC####C#", "#C####CGGGC####C#", "#C####CCCCC####C#", "##CCCC#####CCCC##")
                        .aisle("###CCCCCCCCCCC###", "##C###########C##", "##C###########C##", "##C###########C##", "###CCCCCCCCCCC###")
                        .aisle("####CCCGGGCCC####", "###C#########C###", "###C#########C###", "###C#########C###", "####CCCGGGCCC####")
                        .aisle("######CCCCC######", "####CC#####CC####", "####CC#####CC####", "####CC#####CC####", "######CCCCC######")
                        .aisle("#################", "######CCCCC######", "######CGGGC######", "######CCCCC######", "#################")

                        .where('M', controller(blocks(definition.getBlock())))
                        .where('#', air())
                        .where('G', glass)
                        .where('C', casing)
                        .build();
                    })
            .workableCasingRenderer(GTCEu.id("block/casings/fusion/fusion_casing_mk3"), GTCEu.id("block/multiblock/fusion_reactor"))
            .register();

    public static void init() {}

}
