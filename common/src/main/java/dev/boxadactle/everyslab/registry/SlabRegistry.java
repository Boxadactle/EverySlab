package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneSlab;
import dev.boxadactle.everyslab.variants.SlabVariant;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;

public class SlabRegistry extends EverySlab.VariantRegistry<SlabBlock> {

    public SlabRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneSlab::new);
    }

    @Override
    protected EverySlabBlockProvider<SlabBlock> getProvider() {
        return SlabVariant::new;
    }

    @Override
    protected String append() {
        return "_slab";
    }
}
