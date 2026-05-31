package dev.boxadactle.everyslab.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.apache.commons.lang3.function.TriConsumer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlockTagsGenerator extends BlockTagsProvider {
    public BlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, EverySlab.MOD_ID);
    }

    private HashMap<String, List<String>> tagCache = new HashMap<>();

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // we have to research block tags like this because
        // they are not loaded yet when this runs, and we
        // need to know which tool tags to add the new blocks to
        loadTag("mineable/axe");
        loadTag("mineable/pickaxe");
        loadTag("mineable/shovel");
        loadTag("mineable/hoe");

        loadTag("needs_diamond_tool");
        loadTag("needs_iron_tool");
        loadTag("needs_stone_tool");

        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            Identifier baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            Block hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.blockFromBaseBlock(baseLocation) : null;
            Block hasFence = EverySlab.FENCES.hasVariant(baseLocation) ? EverySlab.FENCES.blockFromBaseBlock(baseLocation) : null;
            Block hasSlab = EverySlab.SLABS.hasVariant(baseLocation) ? EverySlab.SLABS.blockFromBaseBlock(baseLocation) : null;
            Block hasStair = EverySlab.STAIRS.hasVariant(baseLocation) ? EverySlab.STAIRS.blockFromBaseBlock(baseLocation) : null;
            Block hasWall = EverySlab.WALLS.hasVariant(baseLocation) ? EverySlab.WALLS.blockFromBaseBlock(baseLocation) : null;

            TriConsumer<Block, MiningLevel, MiningTool> addMiningTags = ((block, miningLevel, miningTool) -> {
                if (miningLevel != null) tag(miningLevel.tag).add(block);
                if (miningTool != null) tag(miningTool.tag).add(block);
            });

            MiningLevel level = getCorrectMiningLevel(base);
            MiningTool tool = getCorrectTool(base);

            if (hasFenceGate != null) {
                tag(BlockTags.FENCE_GATES).add(hasFenceGate);
                addMiningTags.accept(hasFenceGate, level, tool);
            }

            if (hasFence != null) {
                tag(BlockTags.FENCES).add(hasFence);
                addMiningTags.accept(hasFence, level, tool);
            }

            if (hasSlab != null) {
                tag(BlockTags.SLABS).add(hasSlab);
                addMiningTags.accept(hasSlab, level, tool);
            }

            if (hasStair != null) {
                tag(BlockTags.STAIRS).add(hasStair);
                addMiningTags.accept(hasStair, level, tool);
            }

            if (hasWall != null) {
                tag(BlockTags.WALLS).add(hasWall);
                addMiningTags.accept(hasWall, level, tool);
            }
        });
    }

    public void loadTag(String tag) {
        String path = "data/minecraft/tags/block/" + tag + ".json";
        try (InputStream is = Minecraft.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) return;
            JsonObject object = JsonParser.parseReader(new InputStreamReader(is)).getAsJsonObject();
            JsonArray blocks = object.getAsJsonArray("values");
            if (blocks != null && !blocks.isEmpty()) {
                Constants.LOG.info("Loaded tag " + tag + " with " + blocks.size() + " blocks.");
                tagCache.put(tag, blocks.asList().stream().map(JsonElement::getAsString).toList());
                return;
            } else {
                Constants.LOG.info("Loaded tag " + tag + " with no blocks.");
            }

        } catch (Exception ignored) {
        }
        tagCache.put(tag, List.of());
    }

    private MiningTool getCorrectTool(Block baseBlock) {
        boolean isAxeMineable = tagCache.get("mineable/axe").contains(BuiltInRegistries.BLOCK.getKey(baseBlock).toString());
        boolean isPickaxeMineable = tagCache.get("mineable/pickaxe").contains(BuiltInRegistries.BLOCK.getKey(baseBlock).toString());
        boolean isShovelMineable = tagCache.get("mineable/shovel").contains(BuiltInRegistries.BLOCK.getKey(baseBlock).toString());
        boolean isHoeMineable = tagCache.get("mineable/hoe").contains(BuiltInRegistries.BLOCK.getKey(baseBlock).toString());

        if (isAxeMineable) return MiningTool.AXE;
        if (isPickaxeMineable) return MiningTool.PICKAXE;
        if (isShovelMineable) return MiningTool.SHOVEL;
        if (isHoeMineable) return MiningTool.HOE;

        return null;
    }

    private MiningLevel getCorrectMiningLevel(Block baseBlock) {
        boolean isStoneToolRequired = tagCache.get("needs_stone_tool").contains(BuiltInRegistries.BLOCK.getKey(baseBlock).toString());
        boolean isIronToolRequired = tagCache.get("needs_iron_tool").contains(BuiltInRegistries.BLOCK.getKey(baseBlock).toString());
        boolean isDiamondToolRequired = tagCache.get("needs_diamond_tool").contains(BuiltInRegistries.BLOCK.getKey(baseBlock).toString());

        if (isStoneToolRequired) return MiningLevel.STONE;
        if (isIronToolRequired) return MiningLevel.IRON;
        if (isDiamondToolRequired) return MiningLevel.DIAMOND;

        return null;
    }

    enum MiningTool {
        PICKAXE(BlockTags.MINEABLE_WITH_PICKAXE),
        AXE(BlockTags.MINEABLE_WITH_AXE),
        SHOVEL(BlockTags.MINEABLE_WITH_SHOVEL),
        HOE(BlockTags.MINEABLE_WITH_HOE);

        public TagKey<Block> tag;

        MiningTool(TagKey<Block> tag) {
            this.tag = tag;
        }
    }

    enum MiningLevel {
        STONE(BlockTags.NEEDS_STONE_TOOL),
        IRON(BlockTags.NEEDS_IRON_TOOL),
        DIAMOND(BlockTags.NEEDS_DIAMOND_TOOL);

        public TagKey<Block> tag;

        MiningLevel(TagKey<Block> tag) {
            this.tag = tag;
        }
    }
}
