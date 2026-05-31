package dev.boxadactle.everyslab.mixin;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.registry.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(ItemBlockRenderTypes.class)
public class ItemBlockRenderTypesMixin {

    @Shadow
    @Final
    private static Map<Block, ChunkSectionLayer> TYPE_BY_BLOCK;

    @Inject(method = "<clinit>*", at = @At("RETURN"))
    private static void onInitialize(CallbackInfo info) {
        // make the variants copy the rendering of the source block
        HashMap<Block, ChunkSectionLayer> customRenderTypes = new HashMap<>();
        TYPE_BY_BLOCK.forEach((base, renderType) -> {
            ResourceLocation baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            Block hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.blockFromBaseBlock(baseLocation) : null;
            Block hasFence = EverySlab.FENCES.hasVariant(baseLocation) ? EverySlab.FENCES.blockFromBaseBlock(baseLocation) : null;
            Block hasSlab = EverySlab.SLABS.hasVariant(baseLocation) ? EverySlab.SLABS.blockFromBaseBlock(baseLocation) : null;
            Block hasStair = EverySlab.STAIRS.hasVariant(baseLocation) ? EverySlab.STAIRS.blockFromBaseBlock(baseLocation) : null;
            Block hasWall = EverySlab.WALLS.hasVariant(baseLocation) ? EverySlab.WALLS.blockFromBaseBlock(baseLocation) : null;

            customRenderTypes.put(hasFenceGate, renderType);
            customRenderTypes.put(hasFence, renderType);
            customRenderTypes.put(hasSlab, renderType);
            customRenderTypes.put(hasStair, renderType);
            customRenderTypes.put(hasWall, renderType);
        });

        TYPE_BY_BLOCK.putAll(customRenderTypes);
    }

}
