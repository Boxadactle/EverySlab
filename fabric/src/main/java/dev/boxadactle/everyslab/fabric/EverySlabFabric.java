package dev.boxadactle.everyslab.fabric;

import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;

public class EverySlabFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.IS_DEV = FabricLoader.getInstance().isDevelopmentEnvironment();
        Constants.CONFIG_PATH = FabricLoader.getInstance().getConfigDir();

        Constants.LOG.info("Initializing EverySlab!");
        EverySlab.init();

        if (ModConfig.shouldGenerateFenceGates()) EverySlab.FENCE_GATES.register(this::register);
        if (ModConfig.shouldGenerateFences()) EverySlab.FENCES.register(this::register);
        if (ModConfig.shouldGenerateSlabs()) EverySlab.SLABS.register(this::register);
        if (ModConfig.shouldGenerateStairs()) EverySlab.STAIRS.register(this::register);
        if (ModConfig.shouldGenerateWalls()) EverySlab.WALLS.register(this::register);

        CreativeTabs.registerAll();

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.FENCEGATE_GROUP_KEY).register(group -> {
            EverySlab.FENCE_GATES.forEachBlock(group::accept);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.FENCE_GROUP_KEY).register(group -> {
            EverySlab.FENCES.forEachBlock(group::accept);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.SLAB_GROUP_KEY).register(group -> {
            EverySlab.SLABS.forEachBlock(group::accept);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.STAIR_GROUP_KEY).register(group -> {
            EverySlab.STAIRS.forEachBlock(group::accept);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.WALL_GROUP_KEY).register(group -> {
            EverySlab.WALLS.forEachBlock(group::accept);
        });
    }

    private Pair<Block, Item> register(ResourceLocation location, EverySlabBlockProvider blockFactory, Block base) {
        ResourceKey<Block> blockKey = keyOfBlock(location);
        // Create the block instance
        Block block = blockFactory.getVariant(base, blockKey);

        ResourceKey<Item> itemKey = keyOfItem(location);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

        return new Pair<>(Registry.register(BuiltInRegistries.BLOCK, blockKey, block), blockItem);
    }

    private ResourceKey<Block> keyOfBlock(ResourceLocation name) {
        return ResourceKey.create(Registries.BLOCK, name);
    }

    private ResourceKey<Item> keyOfItem(ResourceLocation name) {
        return ResourceKey.create(Registries.ITEM, name);
    }

}
