package dev.boxadactle.everyslab.fabric.datagen;

import dev.boxadactle.everyslab.datagen.util.LanguageHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
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

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class FabricLangGenerator extends FabricLanguageProvider {

    @FunctionalInterface
    public interface LanguageConstructor {
        LanguageHelper.Provider create(LanguageHelper helper);
    }

    LanguageConstructor constructor;

    public FabricLangGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup, LanguageConstructor provider, String locale) {
        super(dataOutput, locale, registryLookup);
        this.constructor = provider;
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder b) {
        LanguageHelper.Provider provider = constructor.create(new LanguageHelper() {
            @Override
            public void addBlock(Supplier<? extends Block> key, String name) {
                b.add(key.get(), name);
            }

            @Override
            public void add(Block key, String name) {
                b.add(key, name);
            }

            @Override
            public void addItem(Supplier<? extends Item> key, String name) {
                b.add(key.get(), name);
            }

            @Override
            public void add(Item key, String name) {
                b.add(key, name);
            }

            @Override
            public void addItemStack(Supplier<ItemStack> key, String name) {
                b.add(key.get().getItem(), name);
            }

            @Override
            public void add(ItemStack key, String name) {
                b.add(key.getItem(), name);
            }

            @Override
            public void addEffect(Supplier<? extends MobEffect> key, String name) {
                b.add(key.get(), name);
            }

            @Override
            public void add(MobEffect key, String name) {
                b.add(key, name);
            }

            @Override
            public void addEntityType(Supplier<? extends EntityType<?>> key, String name) {
                b.add(key.get(), name);
            }

            @Override
            public void add(EntityType<?> key, String name) {
                b.add(key, name);
            }

            @Override
            public void addTag(Supplier<? extends TagKey<?>> key, String name) {
                b.add(key.get(), name);
            }

            @Override
            public void add(TagKey<?> tagKey, String name) {
                b.add(tagKey, name);
            }

            @Override
            public void add(String key, String value) {
                b.add(key, value);
            }

            @Override
            public void add(String key, Component value) {
                b.add(key, String.valueOf(value));
            }

            @Override
            public void addDimension(ResourceKey<Level> dimension, String value) {
            }

            @Override
            public void addBiome(ResourceKey<Biome> biome, String value) {
            }
        });

        provider.addTranslations();
    }

}
