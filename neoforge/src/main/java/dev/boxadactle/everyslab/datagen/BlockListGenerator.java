package dev.boxadactle.everyslab.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class BlockListGenerator implements DataProvider {
    private final PackOutput output;

    public BlockListGenerator(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        JsonArray blocks = new JsonArray();

        EverySlab.FILTERED_BLOCKS.forEach(block -> blocks.add(BuiltInRegistries.BLOCK.getKey(block).toString()));

        JsonObject obj = new JsonObject();
        obj.add("generated", blocks);

        Path path = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                .resolve(EverySlab.MOD_ID)
                .resolve("generated.json");

        return DataProvider.saveStable(cachedOutput, obj, path);
    }

    @Override
    public String getName() {
        return "EverySlab block list";
    }
}
