package dev.boxadactle.everyslab.neoforge.datagen;

import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class NeoLootGenerator extends BlockLootSubProvider {
    public NeoLootGenerator(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EverySlab.getAllBlocks();
    }

    @Override
    public void generate() {
        EverySlab.getAllBlocks().forEach(this::dropSelf);
    }
}
