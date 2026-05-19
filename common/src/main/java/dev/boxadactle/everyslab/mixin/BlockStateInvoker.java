package dev.boxadactle.everyslab.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Block.class)
public interface BlockStateInvoker {

    @Invoker("createBlockStateDefinition")
    void applyBlockState(StateDefinition.Builder<Block, BlockState> p_323673_);

}
