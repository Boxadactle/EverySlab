package dev.boxadactle.everyslab.variants;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class StairVariant extends StairBlock {
    public StairVariant(Block block) {
        super(block.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(block));
    }
}
