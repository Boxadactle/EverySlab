package dev.boxadactle.everyslab;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.boxadactle.everyslab.mixin.BlockStateInvoker;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.phys.shapes.Shapes;

import java.io.InputStream;
import java.io.InputStreamReader;

public class EverySlabClient {

    public static boolean shouldRegisterBlock(Block block) {
        boolean bl = true;
        bl &= ((ShapeAccessor) block).getPlacholderShape_everyslab().equals(Shapes.block());
        bl &= ((ShapeAccessor) block).hasCollision_everyslab();
        bl &= !(block instanceof EntityBlock);
        bl &= !(block instanceof SimpleWaterloggedBlock);
        bl &= !hasCustomProperties(block);
        bl &= !EverySlabClient.hasMultipleTextures(block);
        return bl;
    }

    private static boolean hasCustomProperties(Block block) {
        try {
            ((BlockStateInvoker) block).applyBlockState(null);
            return false;
        } catch (NullPointerException ignored) {
            return true;
        }
    }

    private static boolean hasMultipleTextures(Block block) {
        ResourceLocation location = ModelLocationUtils.getModelLocation(block);
        String path = "assets/" + location.getNamespace() + "/models/" + location.getPath() + ".json";
        try (InputStream is = EverySlab.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) return false;
            JsonObject json = JsonParser.parseReader(new InputStreamReader(is)).getAsJsonObject();
            // Check if textures object has more than one entry
            if (json.has("textures")) {
                JsonObject textures = json.getAsJsonObject("textures");
                return textures.size() > 1;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
