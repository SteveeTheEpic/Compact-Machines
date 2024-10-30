package com.stevee.CompactMachines.api.machine.multiblock;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.IntInputWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.lowdragmc.lowdraglib.gui.widget.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;



public class PCBFactoryMachine extends WorkableElectricMultiblockMachine {

    private int efficiency = 100;

    public PCBFactoryMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public @NotNull Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        group.addWidget(new IntInputWidget(45, 100, 100, 4, supplier(), consumer()))
                .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()))
                .addWidget(new ComponentPanelWidget(4, 17, this::addDisplayText)
                        .textSupplier(Objects.requireNonNull(this.getLevel()).isClientSide ? null : this::addDisplayText)
                        .setMaxWidthLimit(200)
                        .clickHandler(this::handleDisplayClick));
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);;
        return group;
    }

    private Supplier<Integer> supplier() {

        if (efficiency > 100)
            efficiency = 100;

        if (efficiency <= 1)
            efficiency = 1;

        return () -> efficiency;
    }

    public Integer getEfficiency() {
        return this.efficiency;
    }

    private Consumer<Integer> consumer() {
        return (i) -> {
            if (i > 100)
                i = 100;
            if (i < 1)
                i = 1;

            this.efficiency = i;
        };
    }
}
