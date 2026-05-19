package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.lamp.RedstoneLampWall;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneWall;
import dev.boxadactle.everyslab.variants.WallVariant;
import net.minecraft.world.level.block.Blocks;
import oshi.util.tuples.Pair;

public class WallRegistry extends EverySlab.VariantRegistry {

    public WallRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneWall::new);
        createVariant(Blocks.REDSTONE_LAMP, RedstoneLampWall::new);
    }

    @Override
    protected EverySlabBlockProvider getProvider() {
        return WallVariant::new;
    }

    @Override
    protected String append() {
        return "_wall";
    }
}
