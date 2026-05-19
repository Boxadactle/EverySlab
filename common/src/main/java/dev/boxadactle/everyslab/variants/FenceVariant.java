package dev.boxadactle.everyslab.variants;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FenceVariant extends FenceBlock {
    public FenceVariant(Block block, ResourceKey<Block> id) {
        super(BlockBehaviour.Properties.ofFullCopy(block).setId(id));
    }
}
