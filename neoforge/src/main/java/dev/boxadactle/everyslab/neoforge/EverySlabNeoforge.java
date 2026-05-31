package dev.boxadactle.everyslab.neoforge;

import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.datagen.*;
import dev.boxadactle.everyslab.datagen.localization.EnUsProvider;
import dev.boxadactle.everyslab.datagen.localization.EsEsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod(Constants.MOD_ID)
public class EverySlabNeoforge {

    public EverySlabNeoforge(IEventBus eventBus) {
        Constants.IS_DEV = !FMLEnvironment.production;
        Constants.CONFIG_PATH = FMLPaths.CONFIGDIR.get();

        if (Constants.IS_DEV) eventBus.addListener(EverySlabNeoforge::gatherData);
    }

    public static void gatherData(GatherDataEvent event) {
        // See below for more details on each of these.
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new BlockListGenerator(output));
        generator.addProvider(event.includeServer(), new BlockTagsGenerator(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(LootTableGenerator::new, LootContextParamSets.BLOCK))));
        generator.addProvider(event.includeServer(), new RecipeGenerator(output, lookupProvider));

        generator.addProvider(event.includeClient(), new ModelGenerator(output, existingFileHelper));

        generator.addProvider(event.includeClient(), new EnUsProvider(output));
        generator.addProvider(event.includeClient(), new EsEsProvider(output));
    }
}