package dev.boxadactle.everyslab.fabric.datagen;

import dev.boxadactle.everyslab.datagen.BlockTagsGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class FabricBlockTagsGenerator extends FabricTagsProvider.BlockTagsProvider {
    public FabricBlockTagsGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        BlockTagsGenerator generator = new BlockTagsGenerator(this::valueLookupBuilder);

        generator.addTags(wrapperLookup);
    }
}
