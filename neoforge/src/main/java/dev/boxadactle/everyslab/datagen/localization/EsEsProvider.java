package dev.boxadactle.everyslab.datagen.localization;

import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EsEsProvider extends LanguageProvider {
    public EsEsProvider(PackOutput output) {
        super(output, EverySlab.MOD_ID, "es_es"); // <-- your locale code here
    }

    @Override
    protected void addTranslations() {
        // load the correct language, so the block names translate correctly
        //                your locale code       reads right to left? (Arabic/Hebrew etc.)
        LangUtil.injectLanguage("es_es", false);

        // Creative tab names
        add("itemGroup.everyslab_fencegates", "Variantes de puerta de valla");
        add("itemGroup.everyslab_fences", "Variantes de valla");
        add("itemGroup.everyslab_slabs", "Variantes de losa");
        add("itemGroup.everyslab_stairs", "Variantes de escaleras");
        add("itemGroup.everyslab_walls", "Variantes de muro");

        // Block name suffixes — these are appended to the base block's translated name
        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            Identifier baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            Identifier hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.fromBaseBlock(baseLocation) : null;
            Identifier hasFence    = EverySlab.FENCES.hasVariant(baseLocation)      ? EverySlab.FENCES.fromBaseBlock(baseLocation)      : null;
            Identifier hasSlab     = EverySlab.SLABS.hasVariant(baseLocation)       ? EverySlab.SLABS.fromBaseBlock(baseLocation)        : null;
            Identifier hasStair    = EverySlab.STAIRS.hasVariant(baseLocation)      ? EverySlab.STAIRS.fromBaseBlock(baseLocation)       : null;
            Identifier hasWall     = EverySlab.WALLS.hasVariant(baseLocation)       ? EverySlab.WALLS.fromBaseBlock(baseLocation)        : null;

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