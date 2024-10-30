package com.stevee.CompactMachines.data.recipes;

import com.stevee.CompactMachines.data.recipes.misc.PCBFactory;
import net.minecraft.data.recipes.FinishedRecipe;

import javax.print.attribute.standard.Finishings;
import java.util.function.Consumer;

public class CMRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        PCBFactory.init(provider);
    }

}
