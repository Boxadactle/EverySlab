package dev.boxadactle.everyslab.mixin;

import dev.boxadactle.everyslab.ShapeAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public abstract class InjectBlock extends BlockBehaviour implements ShapeAccessor {
    public InjectBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getPlacholderShape_everyslab() {
        VoxelShape shape = null;
        try {
            shape = this.getShape(null, null, null, null);
        } catch (Throwable ignored) {

        }
        return shape != null ? shape : Shapes.empty();
    }

    public boolean hasCollision_everyslab() {
        return hasCollision;
    }
}
