package dev.boxadactle.everyslab.fabric.datagen;

import dev.boxadactle.everyslab.datagen.ModelGenerator;
import dev.boxadactle.everyslab.datagen.util.ModelHelper;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

public class FabricModelGenerator extends FabricModelProvider {
    public FabricModelGenerator(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockStateModelGenerator) {
        ModelGenerator generator = new ModelGenerator(base -> new ModelHelper.BlockFamilyProvider() {
            final BlockModelGenerators.BlockFamilyProvider family = ((FamilyConstructor)blockStateModelGenerator).everySlab$familyWithExistingFullBlock(base);

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
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerator) {
    }

    public interface FamilyConstructor {
        BlockModelGenerators.BlockFamilyProvider everySlab$familyWithExistingFullBlock(Block fullBlock);
    }
}
