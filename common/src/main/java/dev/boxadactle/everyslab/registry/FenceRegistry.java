package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneFence;
import dev.boxadactle.everyslab.variants.FenceVariant;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;

public class FenceRegistry extends EverySlab.VariantRegistry<FenceBlock> {

    public FenceRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneFence::new);
    }

    @Override
    protected EverySlabBlockProvider<FenceBlock> getProvider() {
        return FenceVariant::new;
    }

    @Override
    protected String append() {
        return "_fence";
    }
}
