package dev.boxadactle.everyslab.fabric.mixin;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.renderer.block.dispatch.Variant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockModelGenerators.BlockFamilyProvider.class)
public interface BlockFamilyProviderAccessor {

    @Accessor("fullBlock")
    void setFullBlock(Variant variant);

}
