package dev.boxadactle.everyslab.fabric.datagen;

import dev.boxadactle.everyslab.datagen.RecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class FabricRecipeGenerator extends FabricRecipeProvider {
    public FabricRecipeGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeGenerator(registryLookup, exporter);
    }

    @Override
    public String getName() {
        return "everyslab recipe provider";
    }
}
