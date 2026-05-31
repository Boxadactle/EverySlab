package dev.boxadactle.everyslab.variants;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class WallVariant extends WallBlock {
    public WallVariant(Block block) {
        super(BlockBehaviour.Properties.ofFullCopy(block));
    }
}
