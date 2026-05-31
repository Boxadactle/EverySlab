package dev.boxadactle.everyslab.registry;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.EverySlabBlockProvider;
import dev.boxadactle.everyslab.blocks.redstoneblock.RedstoneWall;
import dev.boxadactle.everyslab.variants.WallVariant;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBlock;

public class WallRegistry extends EverySlab.VariantRegistry<WallBlock> {

    public WallRegistry() {
        createVariant(Blocks.REDSTONE_BLOCK, RedstoneWall::new);
    }

    @Override
    protected EverySlabBlockProvider<WallBlock> getProvider() {
        return WallVariant::new;
    }

    @Override
    protected String append() {
        return "_wall";
    }
}
