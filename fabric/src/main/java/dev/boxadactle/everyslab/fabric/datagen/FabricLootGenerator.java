package dev.boxadactle.everyslab.fabric.datagen;

import dev.boxadactle.everyslab.EverySlab;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class FabricLootGenerator extends FabricBlockLootSubProvider {

    public FabricLootGenerator(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        EverySlab.getAllBlocks().forEach(this::dropSelf);
    }
}
