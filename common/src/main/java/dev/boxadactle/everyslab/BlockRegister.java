package dev.boxadactle.everyslab;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;

@FunctionalInterface
public interface BlockRegister<T extends Block> {
    Pair<T, Item> registerBlock(ResourceLocation location, EverySlabBlockProvider<T> blockProvider, Block block);

    interface OnlyBlock<T extends Block> {
        void registerBlock(ResourceKey<Block> key, T block);
    }

    interface OnlyBlockItem {
        void registerItem(ResourceKey<Item> key, Item item);
    }
}
