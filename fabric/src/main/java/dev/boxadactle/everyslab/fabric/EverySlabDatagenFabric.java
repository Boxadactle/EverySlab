package dev.boxadactle.everyslab.fabric;

import dev.boxadactle.everyslab.datagen.BlockListGenerator;
import dev.boxadactle.everyslab.datagen.language.EsEsProvider;
import dev.boxadactle.everyslab.fabric.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EverySlabDatagenFabric implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(FabricModelGenerator::new);
        pack.addProvider(FabricBlockTagsGenerator::new);
        pack.addProvider(FabricLootGenerator::new);
        pack.addProvider(FabricRecipeGenerator::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<BlockListGenerator>) BlockListGenerator::new);

        // Add language providers below ↓
        pack.addProvider((output, registries) -> new FabricLangGenerator(output, registries, dev.boxadactle.everyslab.datagen.language.EnUsProvider::new, "en_us"));
        pack.addProvider((output, registries) -> new FabricLangGenerator(output, registries, EsEsProvider::new, "es_es"));
    }
}
