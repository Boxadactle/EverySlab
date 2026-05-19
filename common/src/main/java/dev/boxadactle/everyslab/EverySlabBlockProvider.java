package dev.boxadactle.everyslab;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

@FunctionalInterface
public interface EverySlabBlockProvider {
    Block getVariant(Block block, ResourceKey<Block> id);
}
