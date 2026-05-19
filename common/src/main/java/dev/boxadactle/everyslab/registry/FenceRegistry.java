package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.lamp.RedstoneLampFence;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneFence;
import dev.boxadactle.everyslab.variants.FenceVariant;
import net.minecraft.world.level.block.Blocks;
import oshi.util.tuples.Pair;

public class FenceRegistry extends EverySlab.VariantRegistry {

    public FenceRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneFence::new);
        createVariant(Blocks.REDSTONE_LAMP, RedstoneLampFence::new);
    }

    @Override
    protected EverySlabBlockProvider getProvider() {
        return FenceVariant::new;
    }

    @Override
    protected String append() {
        return "_fence";
    }
}
