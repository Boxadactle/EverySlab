package dev.boxadactle.everyslab.neoforge.datagen;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.datagen.BlockTagsGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class NeoBlockTagsGenerator extends BlockTagsProvider {
    public NeoBlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, EverySlab.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        BlockTagsGenerator generator = new BlockTagsGenerator(NeoBlockTagsGenerator.this::tag);

        generator.addTags(provider);
    }
}
