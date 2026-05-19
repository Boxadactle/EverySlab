package dev.boxadactle.everyslab.variants;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FenceGateVariant extends FenceGateBlock {
    public FenceGateVariant(Block block, ResourceKey<Block> id) {
        super(WoodType.JUNGLE, BlockBehaviour.Properties.ofFullCopy(block).setId(id));
    }
}
