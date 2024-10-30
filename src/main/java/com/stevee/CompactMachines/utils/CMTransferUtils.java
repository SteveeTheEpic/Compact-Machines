package com.stevee.CompactMachines.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class CMTransferUtils {

    /**
     * Gets the FluidHandler from the adjacent block on the side connected to the caller
     *
     * @param level  Level of caller
     * @param pos    BlockPos of caller
     * @param facing Direction to get the FluidHandler from
     * @return LazyOpt of the IFluidHandler described above
     */
    public static LazyOptional<IFluidHandler> getAdjacentFluidHandler(Level level, BlockPos pos, Direction facing) {
        return FluidUtil.getFluidHandler(level, pos.relative(facing), facing.getOpposite());
    }

    // Same as above, but returns the presence
    public static boolean hasAdjacentFluidHandler(Level level, BlockPos pos, Direction facing) {
        return getAdjacentFluidHandler(level, pos, facing).isPresent();
    }

    /**
     * Get the ItemHandler Capability from the given block
     *
     * @param level Level of block
     * @param pos   BlockPos of block
     * @param side  Side of block
     * @return LazyOpt of ItemHandler of given block
     */
    public static LazyOptional<IItemHandler> getItemHandler(Level level, BlockPos pos, @Nullable Direction side) {
        BlockState state = level.getBlockState(pos);
        if (state.hasBlockEntity()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity != null) {
                return blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, side);
            }
        }
        return LazyOptional.empty();
    }

    // Same as getAdjacentFluidHandler, but for ItemHandler
    public static LazyOptional<IItemHandler> getAdjacentItemHandler(Level level, BlockPos pos, Direction facing) {
        return getItemHandler(level, pos.relative(facing), facing.getOpposite());
    }

    // Same as above, but returns the presence
    public static boolean hasAdjacentItemHandler(Level level, BlockPos pos, Direction facing) {
        return getAdjacentItemHandler(level, pos, facing).isPresent();
    }

}
