package dev.boxadactle.everyslab.blocks.lamp;

import dev.boxadactle.everyslab.variants.FenceVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;

public class RedstoneLampFence extends FenceVariant {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public RedstoneLampFence(Block block, ResourceKey<Block> id) {
        super(block, id);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    protected void neighborChanged(BlockState p_55666_, Level p_55667_, BlockPos p_55668_, Block p_55669_,  Orientation p_364297_, boolean p_55671_) {
        if (!p_55667_.isClientSide) {
            boolean flag = p_55666_.getValue(LIT);
            if (flag != p_55667_.hasNeighborSignal(p_55668_)) {
                if (flag) {
                    p_55667_.scheduleTick(p_55668_, this, 4);
                } else {
                    p_55667_.setBlock(p_55668_, p_55666_.cycle(LIT), 2);
                }
            }
        }
        super.neighborChanged(p_55666_, p_55667_, p_55668_, p_55669_, p_364297_, p_55671_);
    }

    protected void tick(BlockState p_221937_, ServerLevel p_221938_, BlockPos p_221939_, RandomSource p_221940_) {
        if (p_221937_.getValue(LIT) && !p_221938_.hasNeighborSignal(p_221939_)) {
            p_221938_.setBlock(p_221939_, p_221937_.cycle(LIT), 2);
        }
        super.tick(p_221937_, p_221938_, p_221939_, p_221940_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        super.createBlockStateDefinition(builder);
    }
}
