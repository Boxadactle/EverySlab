package dev.boxadactle.everyslab.blocks.redstoneblock;

import dev.boxadactle.everyslab.variants.WallVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneWall extends WallVariant {
    public RedstoneWall(Block block, ResourceKey<Block> id) {
        super(block, id);
    }

    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        // 1/3 of full redstone block
        return 5;
    }
}
