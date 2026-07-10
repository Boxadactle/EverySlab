package dev.boxadactle.everyslab.datagen.util;

import net.minecraft.world.level.block.Block;

public interface ModelHelper {

    BlockFamilyProvider familyWithExistingFullBlock(Block base);

    interface BlockFamilyProvider {
        void fenceGate(Block fenceGateBlock);
        void fence(Block fenceBlock);
        void slab(Block slabBlock);
        void stairs(Block stairBlock);
        void wall(Block wallBlock);
    }

}
