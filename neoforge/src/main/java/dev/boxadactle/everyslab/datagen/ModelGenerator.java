package dev.boxadactle.everyslab.datagen;

import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModelGenerator extends BlockStateProvider {
    public ModelGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Constants.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            ResourceLocation baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            ResourceLocation hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasFence = EverySlab.FENCES.hasVariant(baseLocation) ? EverySlab.FENCES.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasSlab = EverySlab.SLABS.hasVariant(baseLocation) ? EverySlab.SLABS.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasStair = EverySlab.STAIRS.hasVariant(baseLocation) ? EverySlab.STAIRS.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasWall = EverySlab.WALLS.hasVariant(baseLocation) ? EverySlab.WALLS.fromBaseBlock(baseLocation) : null;

            // Our mixin makes this work for blocks that have unconventionally named textures
            // such as magma_block being named magma.png
            ResourceLocation baseTexture = TextureMapping.getBlockTexture(base);

            if (hasFenceGate != null) fenceGateBlock(EverySlab.FENCE_GATES.getBlock(hasFenceGate), baseTexture);
            if (hasFence != null) fenceBlock(EverySlab.FENCES.getBlock(hasFence), baseTexture);
            if (hasSlab != null) slabBlock(EverySlab.SLABS.getBlock(hasSlab), baseTexture, baseTexture);
            if (hasStair != null) stairsBlock(EverySlab.STAIRS.getBlock(hasStair), baseTexture);
            if (hasWall != null) wallBlock(EverySlab.WALLS.getBlock(hasWall), baseTexture);
        });
    }
}
