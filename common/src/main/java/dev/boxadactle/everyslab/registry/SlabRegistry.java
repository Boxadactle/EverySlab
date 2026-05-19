package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.lamp.RedstoneLampSlab;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneSlab;
import dev.boxadactle.everyslab.variants.SlabVariant;
import net.minecraft.world.level.block.Blocks;

public class SlabRegistry extends EverySlab.VariantRegistry {

    public SlabRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneSlab::new);
        createVariant(Blocks.REDSTONE_LAMP, RedstoneLampSlab::new);
    }

    @Override
    protected EverySlabBlockProvider getProvider() {
        return SlabVariant::new;
    }

    @Override
    protected String append() {
        return "_slab";
    }
}
