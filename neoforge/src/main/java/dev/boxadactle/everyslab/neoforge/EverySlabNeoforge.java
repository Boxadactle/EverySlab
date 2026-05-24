package dev.boxadactle.everyslab.neoforge;

import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.datagen.*;
import dev.boxadactle.everyslab.datagen.localization.*;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

@Mod(Constants.MOD_ID)
public class EverySlabNeoforge {

    public EverySlabNeoforge(IEventBus eventBus) {
        Constants.IS_DEV = !FMLEnvironment.production;
        Constants.CONFIG_PATH = FMLPaths.CONFIGDIR.get();

        if (Constants.IS_DEV) eventBus.addListener(EverySlabNeoforge::gatherData);
    }

    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModelGenerator::new);
        event.createProvider(BlockTagsGenerator::new);
        event.createProvider((output, lookupProvider) -> new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(
                LootTableGenerator::new,
                LootContextParamSets.BLOCK
        )), lookupProvider));
        event.createProvider(RecipeGenerator.Runner::new);
        event.createProvider(BlockListGenerator::new);

        // language providers
        event.createProvider(EnUsProvider::new);
        event.createProvider(EsEsProvider::new);
    }
}