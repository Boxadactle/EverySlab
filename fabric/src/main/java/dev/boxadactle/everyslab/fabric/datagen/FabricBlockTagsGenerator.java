package dev.boxadactle.everyslab.fabric.datagen;

import dev.boxadactle.everyslab.datagen.BlockTagsGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class FabricBlockTagsGenerator extends FabricTagProvider.BlockTagProvider {
    public FabricBlockTagsGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        BlockTagsGenerator generator = new BlockTagsGenerator(this::valueLookupBuilder);

        generator.addTags(wrapperLookup);
    }
}
