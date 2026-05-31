package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneFenceGate;
import dev.boxadactle.everyslab.variants.FenceGateVariant;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;

public class FenceGateRegistry extends EverySlab.VariantRegistry<FenceGateBlock> {

    public FenceGateRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneFenceGate::new);
    }

    @Override
    protected EverySlabBlockProvider<FenceGateBlock> getProvider() {
        return FenceGateVariant::new;
    }

    @Override
    protected String append() {
        return "_fence_gate";
    }
}
