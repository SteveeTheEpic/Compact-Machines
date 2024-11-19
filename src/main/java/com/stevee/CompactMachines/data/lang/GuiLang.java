package com.stevee.CompactMachines.data.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.tterrag.registrate.providers.RegistrateLangProvider;

public class GuiLang {


    public static void init(RegistrateLangProvider provider) {
        initGuiLang(provider);
    }

    private static void initGuiLang(RegistrateLangProvider provider) {
        LangHandler.replace(provider, "compactmachines.pcb_factory", "PCB Factory V1.01a");
        LangHandler.replace(provider, "compactmachines.circuit_factory", "Circuit Factory V1.01a");
    }

}
