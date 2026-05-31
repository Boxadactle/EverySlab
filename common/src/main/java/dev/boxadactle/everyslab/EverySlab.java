package dev.boxadactle.everyslab;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.boxadactle.everyslab.registry.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import oshi.util.tuples.Pair;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class EverySlab {

    public static final String MOD_ID = "everyslab";

    public static List<Block> FILTERED_BLOCKS = new ArrayList<>();

    public static final VariantRegistry<FenceGateBlock> FENCE_GATES = new FenceGateRegistry();
    public static final VariantRegistry<FenceBlock> FENCES = new FenceRegistry();
    public static final VariantRegistry<SlabBlock> SLABS = new SlabRegistry();
    public static final VariantRegistry<StairBlock> STAIRS = new StairRegistry();
    public static final VariantRegistry<WallBlock> WALLS = new WallRegistry();

    static {
        FILTERED_BLOCKS.add(Blocks.REDSTONE_BLOCK);
    }

    public static void init() {
        List<Block> generated = getGeneratedBlocksIfExists();
        if (generated == null) BuiltInRegistries.BLOCK.forEach(block -> {
            if (BuiltInRegistries.BLOCK.getKey(block).getPath().contains("redstone_lamp")) return;
            ResourceLocation blo = BuiltInRegistries.BLOCK.getKey(block);

            // maybe this will be a feature one day
            if (!blo.getNamespace().equals("minecraft")) return;
            if (blo.getPath().contains("bedrock")) return;
            try {
                // filter for only full and solid blocks
                if (EverySlabClient.shouldRegisterBlock(block)) FILTERED_BLOCKS.add(block);
            } catch (Throwable ignored) {
            }
        });
        else FILTERED_BLOCKS = generated;

        FILTERED_BLOCKS = FILTERED_BLOCKS.stream().distinct().toList();

        FILTERED_BLOCKS.forEach(block -> {
            FENCE_GATES.createVariant(block);
            FENCES.createVariant(block);
            SLABS.createVariant(block);
            STAIRS.createVariant(block);
            WALLS.createVariant(block);
        });
    }

    private static List<Block> getGeneratedBlocksIfExists() {
        String path = "data/everyslab/generated.json";
        try (InputStream stream = EverySlab.class.getClassLoader().getResourceAsStream(path)) {
            if (stream == null) return null;
            JsonObject json = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
            JsonArray generated = json.getAsJsonArray("generated");

            List<Block> blocks = new ArrayList<>();
            generated.asList().forEach(id -> blocks.add(BuiltInRegistries.BLOCK.get(new ResourceLocation(id.getAsString()))));

            return blocks;
        } catch (Exception e) {
            Constants.LOG.warn("Unable to read generated registry! If we are not in datagen mode, then this is a bug!");
            return null;
        }
    }

    public static List<Block> getAllBlocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.addAll(FENCE_GATES.blocks());
        blocks.addAll(FENCES.blocks());
        blocks.addAll(WALLS.blocks());
        blocks.addAll(STAIRS.blocks());
        blocks.addAll(SLABS.blocks());

        return blocks;
    }

    public abstract static class VariantRegistry<T extends Block> {

        protected final HashMap<ResourceLocation, Pair<EverySlabBlockProvider<T>, Block>> VARIANTS = new HashMap<>();

        protected HashMap<ResourceLocation, ResourceLocation> VARIANT_BASE = new HashMap<>();
        protected HashMap<ResourceLocation, T> VARIANT_REGISTRY = new HashMap<>();
        protected HashMap<ResourceLocation, Item> VARIANT_ITEM_REGISTRY = new HashMap<>();

        protected abstract EverySlabBlockProvider<T> getProvider();

        protected abstract String append();

        private boolean alreadyExists(Block base) {
            AtomicBoolean bl = new AtomicBoolean(false);

            VARIANTS.values().forEach(pair -> bl.set(bl.get() | BuiltInRegistries.BLOCK.getKey(base).equals(BuiltInRegistries.BLOCK.getKey(pair.getB()))));

            return bl.get();
        }

        public void createVariant(Block block) {
            ResourceLocation location = BuiltInRegistries.BLOCK.getKey(block);
            String str = location.toString();
            if (alreadyExists(block)) return;
            if (
                    BuiltInRegistries.BLOCK.get(new ResourceLocation(str + append())).equals(Blocks.AIR) &&
                    BuiltInRegistries.BLOCK.get(new ResourceLocation(str.substring(0, str.length() - 1) + append())).equals(Blocks.AIR)
            ) createVariant(block, getProvider());
        }

        public void createVariant(Block block, EverySlabBlockProvider<T> provider) {
            ResourceLocation location = BuiltInRegistries.BLOCK.getKey(block);
            ResourceLocation location1 = new ResourceLocation(Constants.MOD_ID, location.getPath() + append());
            VARIANTS.put(location1, new Pair<>(provider, block));
            VARIANT_BASE.put(location1, location);
        }

        public void register(BlockRegister<T> blockRegister) {
            VARIANTS.forEach((resourceLocation, data) -> {
                if (resourceLocation.getPath().contains("redstone_lamp")) return;
                try {
                    Pair<T, Item> reg = blockRegister.registerBlock(resourceLocation, data.getA(), data.getB());
                    VARIANT_REGISTRY.put(resourceLocation, reg.getA());
                    VARIANT_ITEM_REGISTRY.put(resourceLocation, reg.getB());
                } catch (Exception e) {
                    Constants.LOG.error("Failed to register block {}: {}", resourceLocation, e.getMessage());
                }
            });
        }

        public void registerBlocks(BlockRegister.OnlyBlock<T> blockRegister) {
            VARIANTS.forEach((resourceLocation, data) -> {
                if (resourceLocation.getPath().contains("redstone_lamp")) return;
                ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, resourceLocation);
                T block = data.getA().getVariant(data.getB());
                blockRegister.registerBlock(key, block);
                VARIANT_REGISTRY.put(resourceLocation, block);
            });
        }

        public void registerItems(BlockRegister.OnlyBlockItem itemRegister) {
            VARIANTS.forEach((resourceLocation, data) -> {
                if (resourceLocation.getPath().contains("redstone_lamp")) return;
                ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, resourceLocation);
                Block block = VARIANT_REGISTRY.get(resourceLocation);
                Item item = new BlockItem(block, new Item.Properties());
                itemRegister.registerItem(key, item);
                VARIANT_ITEM_REGISTRY.put(resourceLocation, item);
            });
        }

        public T getBlock(ResourceLocation location) {
            return VARIANT_REGISTRY.get(location);
        }

        public Item getBlockItem(ResourceLocation location) {
            return VARIANT_ITEM_REGISTRY.get(location);
        }

        public void forEachBlock(Consumer<Block> blockConsumer) {
            VARIANT_REGISTRY.forEach((resourceLocation, block) -> blockConsumer.accept(block));
        }

        public void forEachId(Consumer<ResourceLocation> idConsumer) {
            VARIANT_REGISTRY.forEach((resourceLocation, block) -> idConsumer.accept(resourceLocation));
        }

        public Block getBaseBlock(ResourceLocation location) {
            return BuiltInRegistries.BLOCK.get(VARIANT_BASE.get(location));
        }

        public ResourceLocation fromBaseBlock(ResourceLocation location) {
            for (ResourceLocation key : VARIANT_BASE.keySet()) {
                if (VARIANT_BASE.get(key).equals(location)) {
                    return key;
                }
            }
            return null;
        }

        public T blockFromBaseBlock(ResourceLocation location) {
            return getBlock(fromBaseBlock(location));
        }

        public boolean hasVariant(ResourceLocation base) {
            return VARIANT_BASE.containsValue(base);
        }

        public Collection<? extends Block> blocks() {
            return VARIANT_REGISTRY.values();
        }

        public Collection<? extends Item> items() {
            return VARIANT_ITEM_REGISTRY.values();
        }

        protected ResourceLocation id(String path) {
            return new ResourceLocation(Constants.MOD_ID, path);
        }
    }
}