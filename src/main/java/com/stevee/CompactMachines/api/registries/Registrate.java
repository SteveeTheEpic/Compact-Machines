package com.stevee.CompactMachines.api.registries;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.stevee.CompactMachines.CompactMachinesMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class Registrate {

    public static GTRegistrate REGISTRATE = GTRegistrate.create(CompactMachinesMod.MOD_ID);

    static {
        REGISTRATE.defaultCreativeTab((ResourceKey<CreativeModeTab>) null);
    }

    private Registrate() {}
}
