package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.lamp.RedstoneLampStairs;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneStairs;
import dev.boxadactle.everyslab.variants.StairVariant;
import net.minecraft.world.level.block.Blocks;
import oshi.util.tuples.Pair;

public class StairRegistry extends EverySlab.VariantRegistry {

    public StairRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneStairs::new);
        createVariant(Blocks.REDSTONE_LAMP, RedstoneLampStairs::new);
    }

    @Override
    protected EverySlabBlockProvider getProvider() {
        return StairVariant::new;
    }

    @Override
    protected String append() {
        return "_stairs";
    }
}
