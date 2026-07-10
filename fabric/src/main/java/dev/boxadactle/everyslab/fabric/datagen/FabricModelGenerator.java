package dev.boxadactle.everyslab.fabric.datagen;

import dev.boxadactle.everyslab.datagen.ModelGenerator;
import dev.boxadactle.everyslab.datagen.util.ModelHelper;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.level.block.Block;

public class FabricModelGenerator extends FabricModelProvider {
    public FabricModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        ModelGenerator generator = new ModelGenerator(base -> new ModelHelper.BlockFamilyProvider() {
            final BlockModelGenerators.BlockFamilyProvider family = blockStateModelGenerator.family(base);

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

        generator.registerModels(blockStateModelGenerator, null);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
    }
}
