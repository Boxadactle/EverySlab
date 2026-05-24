package dev.boxadactle.everyslab.datagen.localization;

import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EnUsProvider extends LanguageProvider {
    public EnUsProvider(PackOutput output) {
        super(output, EverySlab.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.everyslab_fencegates", "Fence Gate Variants");
        add("itemGroup.everyslab_fences", "Fence Variants");
        add("itemGroup.everyslab_slabs", "Slab Variants");
        add("itemGroup.everyslab_stairs", "Stair Variants");
        add("itemGroup.everyslab_walls", "Wall Variants");

        add("everyslab.registrydesync.1", "EverySlab's client registry is not the same as the server!");
        add("everyslab.registrydesync.2", "Would you like to overwrite the client registry to join this server? (closes the game)");
        add("everyslab.overwrite", "Overwrite");

        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            ResourceLocation baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            ResourceLocation hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasFence = EverySlab.FENCES.hasVariant(baseLocation) ? EverySlab.FENCES.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasSlab = EverySlab.SLABS.hasVariant(baseLocation) ? EverySlab.SLABS.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasStair = EverySlab.STAIRS.hasVariant(baseLocation) ? EverySlab.STAIRS.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasWall = EverySlab.WALLS.hasVariant(baseLocation) ? EverySlab.WALLS.fromBaseBlock(baseLocation) : null;

            if (hasFenceGate != null) add(EverySlab.FENCE_GATES.getBlockItem(hasFenceGate), String.format("%s Fence Gate", base.getName().getString()));
            if (hasFence != null) add(EverySlab.FENCES.getBlockItem(hasFence), String.format("%s Fence", base.getName().getString()));
            if (hasSlab != null) add(EverySlab.SLABS.getBlockItem(hasSlab), String.format("%s Slab", base.getName().getString()));
            if (hasStair != null) add(EverySlab.STAIRS.getBlockItem(hasStair), String.format("%s Stairs", base.getName().getString()));
            if (hasWall != null) add(EverySlab.WALLS.getBlockItem(hasWall), String.format("%s Wall", base.getName().getString()));
        });
    }
}
