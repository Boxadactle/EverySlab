package dev.boxadactle.everyslab.datagen;

import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class LootTableGenerator extends BlockLootSubProvider {

    public LootTableGenerator() {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return EverySlab.getAllBlocks();
    }

    @Override
    protected void generate() {
        getKnownBlocks().forEach(this::dropSelf);
    }
}
