package dev.boxadactle.everyslab.mixin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;
import java.io.InputStreamReader;

@Mixin(TextureMapping.class)
public class TextureMappingMixin {

    @Unique
    private static ResourceLocation everySlab$applyResourceFixes(ResourceLocation block) {
        // waxed copper has same textures as regular
        if (block.getPath().contains("waxed")) {
            block = new ResourceLocation(block.getNamespace(), block.getPath().replace("waxed_", ""));
        }

        // same for infested
        if (block.getPath().contains("infested")) {
            block = new ResourceLocation(block.getNamespace(), block.getPath().replace("infested_", ""));
        }

        return block;
    }

    @Inject(
            method = "getBlockTexture(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/resources/ResourceLocation;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getPathIfExists(Block b, CallbackInfoReturnable<ResourceLocation> cir) {
        if (Constants.IS_DEV) {
            ResourceLocation block = everySlab$applyResourceFixes(BuiltInRegistries.BLOCK.getKey(b));

            if (!EverySlab.FILTERED_BLOCKS.contains(b)) return;

            // read the file
            InputStream stream = Minecraft.class.getClassLoader().getResourceAsStream("assets/" + block.getNamespace() + "/models/block/" + block.getPath() + ".json");
            if (stream == null) {

                Constants.LOG.error("Could not find model file for block: " + block);
                return;
            }
            JsonObject json = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
            JsonObject textures = json.getAsJsonObject("textures");
            String allTexture = textures.get("all").getAsString();
            ResourceLocation mapping = new ResourceLocation(allTexture);
            cir.setReturnValue(mapping);
        }
    }
}
