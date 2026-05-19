package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.lamp.RedstoneLampFenceGate;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneFenceGate;
import dev.boxadactle.everyslab.variants.FenceGateVariant;
import net.minecraft.world.level.block.Blocks;
import oshi.util.tuples.Pair;

public class FenceGateRegistry extends EverySlab.VariantRegistry {

    public FenceGateRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneFenceGate::new);
        createVariant(Blocks.REDSTONE_LAMP, RedstoneLampFenceGate::new);
    }

    @Override
    protected EverySlabBlockProvider getProvider() {
        return FenceGateVariant::new;
    }

    @Override
    protected String append() {
        return "_fence_gate";
    }
}
