package com.stevee.CompactMachines.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.gregtechceu.gtceu.common.data.GTModels;
import com.stevee.CompactMachines.CompactMachinesMod;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.stevee.CompactMachines.api.registries.Registrate.REGISTRATE;

public class CMBlocks {

    public static void init() {
    }

    static {
        REGISTRATE.creativeModeTab(() -> CMCreativeModeTabs.COMPACT_MACHINES);
    }


    public static final BlockEntry<Block> COMPACT_CHEMICAL_REACTOR_CASING = createCasingBlock("compact_chemical_reactor_casing", CompactMachinesMod.id("block/casings/solid/compact_chemical_reactor_casing"));
    public static final BlockEntry<Block> PCB_FACTORY_CASING = createCasingBlock("pcb_factory_casing", CompactMachinesMod.id("block/casings/solid/pcb_factory_casing"));
    public static final BlockEntry<Block> CIRCUIT_FACTORY_CASING = createCasingBlock("circuit_factory_casing", CompactMachinesMod.id("block/casings/solid/circuit_factory_casing"));

    public static final BlockEntry<CoilBlock> UNIVERSIUM_COIL_BLOCK = createCoilBlock(CMCoilBlock.CoilType.UNIVERSIUM);

    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate(GTModels.cubeAllModel(name, texture))
                // Note: Can't access GTToolType and other registries at this point, so just create a new tag, it'll get added anyway
                .tag(TagUtil.createBlockTag("mineable/wrench"), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    private static BlockEntry<CoilBlock> createCoilBlock(ICoilType coilType) {
        BlockEntry<CoilBlock> coilBlock = REGISTRATE.block("%s_coil_block".formatted(coilType.getName()), p -> new CoilBlock(p, coilType))
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate(GTModels.createCoilModel("%s_coil_block".formatted(coilType.getName()), coilType))
                .tag(TagUtil.createBlockTag("mineable/wrench"), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
        GTCEuAPI.HEATING_COILS.put(coilType, coilBlock);
        return coilBlock;
    }
}
