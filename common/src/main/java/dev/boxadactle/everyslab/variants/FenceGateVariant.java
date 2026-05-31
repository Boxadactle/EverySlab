package dev.boxadactle.everyslab.variants;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FenceGateVariant extends FenceGateBlock {
    public FenceGateVariant(Block block) {
        super(WoodType.ACACIA, BlockBehaviour.Properties.ofFullCopy(block));
    }
}
