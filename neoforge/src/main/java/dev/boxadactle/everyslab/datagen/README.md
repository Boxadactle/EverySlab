## 🌍 Translations

EverySlab supports community translations! Since translations are generated via **NeoForge's data generation system**, contributing requires writing a small amount of Java code. Don't worry — it's straightforward and this guide will walk you through every step.

### Prerequisites

- A basic understanding of Java syntax
- A working NeoForge mod development environment with this project set up
- This guide is a paraphrased version of the [NeoForge Docs](https://docs.neoforged.net/docs/resources/client/i18n)

---

### Step 1: Create a Language Provider

Navigate to `neoforge/src/main/java/dev/boxadactle/everyslab/datagen/` and create a new file for your locale. Name it using your locale code — for example, `EsEsProvider.java` for Spanish (Spain).

Use the following template, replacing `es_es` with your locale code and updating the block name translations accordingly:

```java
package dev.boxadactle.everyslab.datagen;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EsEsProvider extends LanguageProvider {
    public EsEsProvider(PackOutput output) {
        super(output, EverySlab.MOD_ID, "es_es"); // <-- your locale code here
    }

    @Override
    protected void addTranslations() {
        // load the correct language, so the block names translate correctly
        //             your locale code    reads right to left? (Arabic/Hebrew etc.)
        LangUtil.injectLanguage("es_es", false);

        // Creative tab names
        add("itemGroup.everyslab_fencegates", "Variantes de puerta de valla");
        add("itemGroup.everyslab_fences", "Variantes de valla");
        add("itemGroup.everyslab_slabs", "Variantes de losa");
        add("itemGroup.everyslab_stairs", "Variantes de escaleras");
        add("itemGroup.everyslab_walls", "Variantes de muro");

        // Block name suffixes — these are appended to the base block's translated name
        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            ResourceLocation baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            ResourceLocation hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasFence    = EverySlab.FENCES.hasVariant(baseLocation)      ? EverySlab.FENCES.fromBaseBlock(baseLocation)      : null;
            ResourceLocation hasSlab     = EverySlab.SLABS.hasVariant(baseLocation)       ? EverySlab.SLABS.fromBaseBlock(baseLocation)        : null;
            ResourceLocation hasStair    = EverySlab.STAIRS.hasVariant(baseLocation)      ? EverySlab.STAIRS.fromBaseBlock(baseLocation)       : null;
            ResourceLocation hasWall     = EverySlab.WALLS.hasVariant(baseLocation)       ? EverySlab.WALLS.fromBaseBlock(baseLocation)        : null;

            // Adjust the suffix strings to match your language's grammar
            if (hasFenceGate != null) add(EverySlab.FENCE_GATES.getBlockItem(hasFenceGate), String.format("Puerta de valla de %s", base.getName().getString()));
            if (hasFence     != null) add(EverySlab.FENCES.getBlockItem(hasFence),           String.format("Valla de  %s",     base.getName().getString()));
            if (hasSlab      != null) add(EverySlab.SLABS.getBlockItem(hasSlab),             String.format("Losa de %s",      base.getName().getString()));
            if (hasStair     != null) add(EverySlab.STAIRS.getBlockItem(hasStair),           String.format("Escaleras de %s",    base.getName().getString()));
            if (hasWall      != null) add(EverySlab.WALLS.getBlockItem(hasWall),             String.format("Muro de %s",      base.getName().getString()));
        });

        // make sure to restore the original language for other language localizations
        LangUtil.restore();
    }
}
```

> ⚠️ **Important:** The block names passed to `String.format()` (e.g. `base.getName().getString()`) come from Minecraft's own localizations, since we used a helper method `LangUtil.injectLanguage("es_es", false);`. Your language's Minecraft installation will substitute the correct translated block name automatically at runtime, so you only need to worry about the **suffix or surrounding words** (e.g. `"Slab"`, `"Fence Gate"`). Adjust word order and grammar as needed for your language.

---

### Step 2: Register Your Provider

Open `EverySlabNeoforge.java` (the mod's main NeoForge class) and find the `GatherDataEvent` handler. It should look something like this:

```java
@SubscribeEvent
public static void gatherData(GatherDataEvent.Client event) {
    event.createProvider(ModelGenerator::new);
    event.createProvider(BlockTagsGenerator::new);
    event.createProvider((output, lookupProvider) -> new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(
            LootTableGenerator::new,
            LootContextParamSets.BLOCK
    )), lookupProvider));
    event.createProvider(RecipeGenerator.Runner::new);

    // language providers
    event.createProvider(EnUsProvider::new);
    // Add your provider on the line below ↓
    generator.addProvider(EsEsProvider::new);
}
```

Add a new `generator.addProvider(...)` line for your class, exactly as shown above.

---

### Step 3: Run Data Generation

Run the `runData` Gradle task to generate your translation file:

```bash
./gradlew runData
```

Your translation will be written to:
`common/src/generated/resources/assets/everyslab/lang/<your_locale>.json`

Open that file and verify the output looks correct before submitting a pull request.

---

### Translation Keys Reference

| Key | Description |
|-----|-------------|
| `itemGroup.everyslab_fencegates` | Creative tab for fence gate variants |
| `itemGroup.everyslab_fences` | Creative tab for fence variants |
| `itemGroup.everyslab_slabs` | Creative tab for slab variants |
| `itemGroup.everyslab_stairs` | Creative tab for stair variants |
| `itemGroup.everyslab_walls` | Creative tab for wall variants |
| `generic fence gate localization` | %s Fence Gate |
| `generic fence localization` | %s Fence |
| `generic slab localization` | %s Slab |
| `generic stair localization` | %s Stairs |
| `generic wall localization` | %s Wall |

---

Not comfortable with Java or setting up a dev environment? **[Open a GitHub issue](https://github.com/Boxadactle/EverySlab/issues)** with your locale and the translated strings for the keys in the table above, they can be integrated in the translation for you.

