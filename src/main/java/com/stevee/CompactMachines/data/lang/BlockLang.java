package com.stevee.CompactMachines.data.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.tterrag.registrate.providers.RegistrateLangProvider;

public class BlockLang {

    public static void init(RegistrateLangProvider provider) {
        initCasingLang(provider);
    }

    private static void initCasingLang(RegistrateLangProvider provider) {
        LangHandler.replace(provider, "block.compactmachines.pcb_factory_casing", "PCB Factory Casing");
        LangHandler.replace(provider, "block.compactmachines.sterile_maintenance_hatch", "Sterile Maintenance Hatch");
    }

}
