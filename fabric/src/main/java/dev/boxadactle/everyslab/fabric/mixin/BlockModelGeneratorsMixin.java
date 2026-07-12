package dev.boxadactle.everyslab.fabric.mixin;

import dev.boxadactle.everyslab.fabric.datagen.FabricModelGenerator;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockModelGenerators.class)
public class BlockModelGeneratorsMixin implements FabricModelGenerator.FamilyConstructor {

    @Override
    public BlockModelGenerators.BlockFamilyProvider everySlab$familyWithExistingFullBlock(Block fullBlock) {
        BlockModelGenerators self = (BlockModelGenerators)(Object)this;
        BlockModelGenerators.BlockFamilyProvider provider = self.new BlockFamilyProvider(TextureMapping.cube(fullBlock));
        ((BlockFamilyProviderAccessor)provider).setFullBlock(BlockModelGenerators.plainModel(ModelLocationUtils.getModelLocation(fullBlock)));
        return provider;
    }
}
