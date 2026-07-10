package dev.boxadactle.everyslab.datagen.localization;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.datagen.util.LanguageHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class EnUsProvider implements LanguageHelper.Provider {
    LanguageHelper provider;

    public EnUsProvider(LanguageHelper provider) {
        this.provider = provider;
    }

    public void addTranslations() {
        provider.add("itemGroup.everyslab_fencegates", "Fence Gate Variants");
        provider.add("itemGroup.everyslab_fences", "Fence Variants");
        provider.add("itemGroup.everyslab_slabs", "Slab Variants");
        provider.add("itemGroup.everyslab_stairs", "Stair Variants");
        provider.add("itemGroup.everyslab_walls", "Wall Variants");

        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            Identifier baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            Identifier hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.fromBaseBlock(baseLocation) : null;
            Identifier hasFence = EverySlab.FENCES.hasVariant(baseLocation) ? EverySlab.FENCES.fromBaseBlock(baseLocation) : null;
            Identifier hasSlab = EverySlab.SLABS.hasVariant(baseLocation) ? EverySlab.SLABS.fromBaseBlock(baseLocation) : null;
            Identifier hasStair = EverySlab.STAIRS.hasVariant(baseLocation) ? EverySlab.STAIRS.fromBaseBlock(baseLocation) : null;
            Identifier hasWall = EverySlab.WALLS.hasVariant(baseLocation) ? EverySlab.WALLS.fromBaseBlock(baseLocation) : null;

            if (hasFenceGate != null) provider.add(EverySlab.FENCE_GATES.getBlockItem(hasFenceGate), String.format("%s Fence Gate", base.getName().getString()));
            if (hasFence != null) provider.add(EverySlab.FENCES.getBlockItem(hasFence), String.format("%s Fence", base.getName().getString()));
            if (hasSlab != null) provider.add(EverySlab.SLABS.getBlockItem(hasSlab), String.format("%s Slab", base.getName().getString()));
            if (hasStair != null) provider.add(EverySlab.STAIRS.getBlockItem(hasStair), String.format("%s Stairs", base.getName().getString()));
            if (hasWall != null) provider.add(EverySlab.WALLS.getBlockItem(hasWall), String.format("%s Wall", base.getName().getString()));
        });
    }
}
