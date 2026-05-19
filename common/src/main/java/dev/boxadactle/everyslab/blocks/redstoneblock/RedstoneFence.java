package dev.boxadactle.everyslab.blocks.redstoneblock;

import dev.boxadactle.everyslab.variants.FenceVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneFence extends FenceVariant {
    public RedstoneFence(Block block, ResourceKey<Block> id) {
        super(block, id);
    }

    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        // 1/3 of full block
        return 5;
    }
}
