package dev.boxadactle.everyslab.datagen;

import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends RecipeProvider {
    protected RecipeGenerator(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        EverySlab.FILTERED_BLOCKS.forEach(base -> {
            ResourceLocation baseLocation = BuiltInRegistries.BLOCK.getKey(base);
            ResourceLocation hasFenceGate = EverySlab.FENCE_GATES.hasVariant(baseLocation) ? EverySlab.FENCE_GATES.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasFence = EverySlab.FENCES.hasVariant(baseLocation) ? EverySlab.FENCES.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasSlab = EverySlab.SLABS.hasVariant(baseLocation) ? EverySlab.SLABS.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasStair = EverySlab.STAIRS.hasVariant(baseLocation) ? EverySlab.STAIRS.fromBaseBlock(baseLocation) : null;
            ResourceLocation hasWall = EverySlab.WALLS.hasVariant(baseLocation) ? EverySlab.WALLS.fromBaseBlock(baseLocation) : null;

            if (hasFenceGate != null) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, EverySlab.FENCE_GATES.getBlock(hasFenceGate), base);

                shaped(RecipeCategory.BUILDING_BLOCKS, EverySlab.FENCE_GATES.getBlock(hasFenceGate), 2)
                        .pattern("/B/")
                        .pattern("/B/")
                        .define('/', Items.STICK)
                        .define('B', base)
                        .unlockedBy(getHasName(Items.CRAFTING_TABLE), has(Items.CRAFTING_TABLE))
                        .save(output);
            }

            if (hasFence != null) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, EverySlab.FENCES.getBlock(hasFence), base);

                shaped(RecipeCategory.BUILDING_BLOCKS, EverySlab.FENCES.getBlock(hasFence), 2)
                        .pattern("B/B")
                        .pattern("B/B")
                        .define('/', Items.STICK)
                        .define('B', base)
                        .unlockedBy(getHasName(Items.CRAFTING_TABLE), has(Items.CRAFTING_TABLE))
                        .save(output);
            }

            if (hasSlab != null) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, EverySlab.SLABS.getBlock(hasSlab), base, 2);

                shaped(RecipeCategory.BUILDING_BLOCKS, EverySlab.SLABS.getBlock(hasSlab), 6)
                        .pattern("BBB")
                        .define('B', base)
                        .unlockedBy(getHasName(Items.CRAFTING_TABLE), has(Items.CRAFTING_TABLE))
                        .save(output);
            }

            if (hasStair != null) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, EverySlab.STAIRS.getBlock(hasStair), base);

                shaped(RecipeCategory.BUILDING_BLOCKS, EverySlab.STAIRS.getBlock(hasStair), 4)
                        .pattern("B  ")
                        .pattern("BB ")
                        .pattern("BBB")
                        .define('B', base)
                        .unlockedBy(getHasName(Items.CRAFTING_TABLE), has(Items.CRAFTING_TABLE))
                        .save(output);
            }

            if (hasWall != null) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, EverySlab.WALLS.getBlock(hasWall), base);

                shaped(RecipeCategory.BUILDING_BLOCKS, EverySlab.WALLS.getBlock(hasWall), 6)
                        .pattern("BBB")
                        .pattern("BBB")
                        .define('B', base)
                        .unlockedBy(getHasName(Items.CRAFTING_TABLE), has(Items.CRAFTING_TABLE))
                        .save(output);
            }
        });
    }

    public static class Runner extends RecipeProvider.Runner {
        // Get the parameters from the `GatherDataEvent`s.
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new RecipeGenerator(provider, output);
        }

        @Override
        public String getName() {
            return "everyslab_recipes";
        }
    }
}
