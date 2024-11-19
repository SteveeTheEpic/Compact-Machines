package com.stevee.CompactMachines.common.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.stevee.CompactMachines.CompactMachinesMod;
import com.stevee.CompactMachines.api.registries.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;

public class CMCreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> COMPACT_MACHINES = Registrate.REGISTRATE.defaultCreativeTab(CompactMachinesMod.MOD_ID,
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(CompactMachinesMod.MOD_ID, Registrate.REGISTRATE))
                    .title(Registrate.REGISTRATE.addLang("item_group", CompactMachinesMod.id("creative_tab"), "Compact Machines"))
                    .icon(CMMachines.CIRCUIT_FACTORY::asStack)
                    .build())
            .register();

    public static void init() {

    }

}
