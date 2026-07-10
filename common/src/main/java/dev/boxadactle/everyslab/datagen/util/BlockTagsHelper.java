package dev.boxadactle.everyslab.datagen.util;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public interface BlockTagsHelper {

    TagAppender<Block, Block> tag(TagKey<Block> key);

}
