package com.stevee.CompactMachines.data;

import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.stevee.CompactMachines.api.registries.Registrate;
import com.tterrag.registrate.providers.ProviderType;

public class CMDataGen {

    public static void init() {
        Registrate.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }

}
