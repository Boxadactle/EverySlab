package dev.boxadactle.everyslab.datagen;

import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class StonecutterGenerator extends RecipeProvider {
    protected StonecutterGenerator(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            ResourceLocation baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            Block hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.blockFromBaseBlock(baseLocation) : null;
            Block hasFence = EverySlab.FENCES.hasVariant(baseLocation) ? EverySlab.FENCES.blockFromBaseBlock(baseLocation) : null;
            Block hasSlab = EverySlab.SLABS.hasVariant(baseLocation) ? EverySlab.SLABS.blockFromBaseBlock(baseLocation) : null;
            Block hasStair = EverySlab.STAIRS.hasVariant(baseLocation) ? EverySlab.STAIRS.blockFromBaseBlock(baseLocation) : null;
            Block hasWall = EverySlab.WALLS.hasVariant(baseLocation) ? EverySlab.WALLS.blockFromBaseBlock(baseLocation) : null;

            if (hasFenceGate != null) stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, hasFenceGate, base);
            if (hasFence != null) stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, hasFence, base);
            if (hasSlab != null) stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, hasSlab, base);
            if (hasStair != null) stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, hasStair, base);
            if (hasWall != null) stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, hasWall, base);
        });
    }

    public static class Runner extends RecipeProvider.Runner {
        // Get the parameters from the `GatherDataEvent`s.
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new StonecutterGenerator(provider, output);
        }

        @Override
        public String getName() {
            return "everyslab_stonecutter_recipes";
        }
    }
}
