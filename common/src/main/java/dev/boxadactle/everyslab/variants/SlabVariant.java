package dev.boxadactle.everyslab.variants;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SlabVariant extends SlabBlock {
    public SlabVariant(Block block, ResourceKey<Block> id) {
        super(BlockBehaviour.Properties.ofFullCopy(block).setId(id));
    }
}
