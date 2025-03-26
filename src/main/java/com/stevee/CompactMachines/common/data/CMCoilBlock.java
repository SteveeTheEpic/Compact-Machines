package com.stevee.CompactMachines.common.data;

import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.stevee.CompactMachines.CompactMachinesMod;
import com.stevee.CompactMachines.common.data.materials.CMElementMaterials;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import net.minecraft.util.StringRepresentable;

import java.util.function.Supplier;

import static com.stevee.CompactMachines.common.data.materials.CMElementMaterials.Universium;

public class CMCoilBlock {
    public enum CoilType implements StringRepresentable, ICoilType {

        UNIVERSIUM("universium", 14001, 20, 8,() -> Universium, CompactMachinesMod.id("block/casings/coils/universium/universium_coil_block"));
        @NotNull
        @Getter
        private final String name;
        // electric blast furnace properties
        @Getter
        private final int coilTemperature;
        // multi smelter properties
        @Getter
        private final int level;
        @Getter
        private final int energyDiscount;
        @Getter
        private final int tier;
        private final Supplier<Material> material;
        @Getter
        private final ResourceLocation texture;

        CoilType(@NotNull String name, int coilTemperature, int level, int energyDiscount, Supplier<Material> material, ResourceLocation texture) {
            this.name = name;
            this.coilTemperature = coilTemperature;
            this.level = level;
            this.tier = energyDiscount;
            this.energyDiscount = energyDiscount;
            this.material = material;
            this.texture = texture;
        }

        public Material getMaterial() {
            return material.get();
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }
}
