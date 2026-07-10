package dev.boxadactle.everyslab.datagen.util;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface LanguageHelper {

    void addBlock(Supplier<? extends Block> key, String name);

    void add(Block key, String name);

    void addItem(Supplier<? extends Item> key, String name);

    void add(Item key, String name);

    void addItemStack(Supplier<ItemStack> key, String name);

    void add(ItemStack key, String name);

    void addEffect(Supplier<? extends MobEffect> key, String name);

    void add(MobEffect key, String name);

    void addEntityType(Supplier<? extends EntityType<?>> key, String name);

    void add(EntityType<?> key, String name);

    void addTag(Supplier<? extends TagKey<?>> key, String name);

    void add(TagKey<?> tagKey, String name);

    void add(String key, String value);

    void add(String key, Component value);

    void addDimension(ResourceKey<Level> dimension, String value);

    void addBiome(ResourceKey<Biome> biome, String value);

    interface Provider {
        void addTranslations();
    }

}
