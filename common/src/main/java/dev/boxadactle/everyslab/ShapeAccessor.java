package dev.boxadactle.everyslab;

import net.minecraft.world.phys.shapes.VoxelShape;

public interface ShapeAccessor {
    VoxelShape getPlacholderShape_everyslab();

    boolean hasCollision_everyslab();
}

