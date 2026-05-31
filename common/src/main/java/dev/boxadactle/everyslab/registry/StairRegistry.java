package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneStairs;
import dev.boxadactle.everyslab.variants.StairVariant;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;

public class StairRegistry extends EverySlab.VariantRegistry<StairBlock> {

    public StairRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneStairs::new);
    }

    @Override
    protected EverySlabBlockProvider<StairBlock> getProvider() {
        return StairVariant::new;
    }

    @Override
    protected String append() {
        return "_stairs";
    }
}
