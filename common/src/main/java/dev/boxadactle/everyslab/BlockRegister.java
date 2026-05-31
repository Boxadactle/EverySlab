package dev.boxadactle.everyslab;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;

@FunctionalInterface
public interface BlockRegister {
    Pair<Block, Item> registerBlock(Identifier location, EverySlabBlockProvider blockProvider, Block block);

    interface OnlyBlock {
        void registerBlock(ResourceKey<Block> key, Block block);
    }

    interface OnlyBlockItem {
        void registerItem(ResourceKey<Item> key, Item item);
    }
}
