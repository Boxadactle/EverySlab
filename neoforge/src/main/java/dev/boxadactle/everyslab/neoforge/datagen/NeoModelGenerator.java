package dev.boxadactle.everyslab.neoforge.datagen;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.datagen.ModelGenerator;
import dev.boxadactle.everyslab.datagen.util.ModelHelper;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;

public class NeoModelGenerator extends ModelProvider {
    public NeoModelGenerator(PackOutput output) {
        super(output, EverySlab.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        ModelGenerator generator = new ModelGenerator(base -> new ModelHelper.BlockFamilyProvider() {
            final BlockModelGenerators.BlockFamilyProvider family = blockModels.familyWithExistingFullBlock(base);

            @Override
            public void fenceGate(Block fenceGateBlock) {
                family.fenceGate(fenceGateBlock);
            }

            @Override
            public void fence(Block fenceBlock) {
                family.fence(fenceBlock);
            }

            @Override
            public void slab(Block slabBlock) {
                family.slab(slabBlock);
            }

            @Override
            public void stairs(Block stairBlock) {
                family.stairs(stairBlock);
            }

            @Override
            public void wall(Block wallBlock) {
                family.wall(wallBlock);
            }
        });

        generator.registerModels(blockModels, itemModels);
    }
}
