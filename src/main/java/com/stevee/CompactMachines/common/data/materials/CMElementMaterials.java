package com.stevee.CompactMachines.common.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.stevee.CompactMachines.CompactMachinesMod;
import com.stevee.CompactMachines.api.data.chemical.material.info.CMMaterialIconSet;
import com.stevee.CompactMachines.common.data.CMElement;
import dev.latvian.mods.kubejs.item.ItemBuilder;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;

public class CMElementMaterials {
    public static void init() {}

    public static final Material Universium = new Material.Builder(CompactMachinesMod.id("universium"))
            .ingot().iconSet(CMMaterialIconSet.UNIVERSIUM).element(CMElement.Un)
            .liquid(new FluidBuilder().temperature(14000).state(FluidState.LIQUID).customStill())
            .cableProperties(GTValues.V[GTValues.MAX] - 1, 64, 0, true)
            .flags(GENERATE_PLATE, GENERATE_FOIL, GENERATE_RING, GENERATE_FRAME, GENERATE_ROTOR, NO_SMASHING)
            .buildAndRegister();
}
