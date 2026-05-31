package dev.boxadactle.everyslab.blocks.redstoneblock;

import dev.boxadactle.everyslab.variants.SlabVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneSlab extends SlabVariant {
    public RedstoneSlab(Block block) {
        super(block);
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 8;
    }
}
