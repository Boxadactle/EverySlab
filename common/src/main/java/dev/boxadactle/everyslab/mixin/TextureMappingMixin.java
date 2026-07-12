package dev.boxadactle.everyslab.mixin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
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
    private static Identifier everySlab$applyResourceFixes(Identifier block) {
        // waxed copper has same textures as regular
        if (block.getPath().contains("waxed")) {
            block = Identifier.fromNamespaceAndPath(block.getNamespace(), block.getPath().replace("waxed_", ""));
        }

        // same for infested
        if (block.getPath().contains("infested")) {
            block = Identifier.fromNamespaceAndPath(block.getNamespace(), block.getPath().replace("infested_", ""));
        }

        return block;
    }

    @Inject(
            method = "getBlockTexture(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/client/resources/model/sprite/Material;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getPathIfExists(Block b, CallbackInfoReturnable<Material> cir) {
        if (Constants.IS_DEV) {
            Identifier block = everySlab$applyResourceFixes(BuiltInRegistries.BLOCK.getKey(b));

            if (!EverySlab.FILTERED_BLOCKS.contains(b)) return;

            // read the file
            InputStream stream = Minecraft.class.getClassLoader().getResourceAsStream("assets/" + block.getNamespace() + "/models/block/" + block.getPath() + ".json");
            if (stream == null) {

                Constants.LOG.error("Could not find model file for block: " + block);
                return;
            }
            try {
                JsonObject json = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
                JsonObject textures = json.getAsJsonObject("textures");
                String allTexture = textures.get("all").getAsString();
                Identifier mapping = Identifier.parse(allTexture);
                cir.setReturnValue(new Material(mapping));
            } catch (UnsupportedOperationException e) {
                Constants.LOG.error("An error occured when parsing model file for block: " + block);
            }
        }
    }
}
