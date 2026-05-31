package dev.boxadactle.everyslab.blocks.redstoneblock;

import dev.boxadactle.everyslab.variants.FenceGateVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneFenceGate extends FenceGateVariant {
    public RedstoneFenceGate(Block block) {
        super(block);
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 4;
    }
}
