package dev.boxadactle.everyslab.neoforge;

import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.datagen.BlockListGenerator;
import dev.boxadactle.everyslab.datagen.RecipeGenerator;
import dev.boxadactle.everyslab.datagen.language.EsEsProvider;
import dev.boxadactle.everyslab.neoforge.datagen.NeoBlockTagsGenerator;
import dev.boxadactle.everyslab.neoforge.datagen.NeoLangProvider;
import dev.boxadactle.everyslab.neoforge.datagen.NeoLootGenerator;
import dev.boxadactle.everyslab.neoforge.datagen.NeoModelGenerator;
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
        Constants.IS_DEV = !FMLEnvironment.isProduction();
        Constants.CONFIG_PATH = FMLPaths.CONFIGDIR.get();

        if (Constants.IS_DEV) eventBus.addListener(EverySlabNeoforge::gatherData);
    }

    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(NeoModelGenerator::new);
        event.createProvider(NeoBlockTagsGenerator::new);
        event.createProvider((output, lookupProvider) -> new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(
                NeoLootGenerator::new,
                LootContextParamSets.BLOCK
        )), lookupProvider));
        event.createProvider(RecipeGenerator.Runner::new);
        event.createProvider(BlockListGenerator::new);

        // Add language providers below ↓
        event.createProvider((output) -> new NeoLangProvider(output, dev.boxadactle.everyslab.datagen.language.EnUsProvider::new, "en_us"));
        event.createProvider((output) -> new NeoLangProvider(output, EsEsProvider::new, "es_es"));
    }
}