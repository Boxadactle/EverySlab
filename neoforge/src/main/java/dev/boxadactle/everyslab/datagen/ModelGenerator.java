package dev.boxadactle.everyslab.datagen;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.registry.*;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

public class ModelGenerator extends ModelProvider {
    public ModelGenerator(PackOutput output) {
        super(output, EverySlab.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            Identifier baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            Identifier hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.fromBaseBlock(baseLocation) : null;
            Identifier hasFence = EverySlab.FENCES.hasVariant(baseLocation) ? EverySlab.FENCES.fromBaseBlock(baseLocation) : null;
            Identifier hasSlab = EverySlab.SLABS.hasVariant(baseLocation) ? EverySlab.SLABS.fromBaseBlock(baseLocation) : null;
            Identifier hasStair = EverySlab.STAIRS.hasVariant(baseLocation) ? EverySlab.STAIRS.fromBaseBlock(baseLocation) : null;
            Identifier hasWall = EverySlab.WALLS.hasVariant(baseLocation) ? EverySlab.WALLS.fromBaseBlock(baseLocation) : null;

            // Our mixin makes this work for blocks that have unconventionally named textures
            // such as magma_block being named magma.png
            BlockModelGenerators.BlockFamilyProvider blockFamily = blockModels.familyWithExistingFullBlock(base);

            if (hasFenceGate != null) blockFamily.fenceGate(EverySlab.FENCE_GATES.getBlock(hasFenceGate));
            if (hasFence != null) blockFamily.fence(EverySlab.FENCES.getBlock(hasFence));
            if (hasSlab != null) blockFamily.slab(EverySlab.SLABS.getBlock(hasSlab));
            if (hasStair != null) blockFamily.stairs(EverySlab.STAIRS.getBlock(hasStair));
            if (hasWall != null) blockFamily.wall(EverySlab.WALLS.getBlock(hasWall));
        });
    }
}
