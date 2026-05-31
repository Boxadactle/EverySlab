package dev.boxadactle.everyslab;

import net.minecraft.world.level.block.Block;

@FunctionalInterface
public interface EverySlabBlockProvider<T extends Block> {
    T getVariant(Block block);
}
