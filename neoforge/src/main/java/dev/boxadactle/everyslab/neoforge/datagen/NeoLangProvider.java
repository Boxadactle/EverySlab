package dev.boxadactle.everyslab.neoforge.datagen;

import dev.boxadactle.everyslab.EverySlab;
import dev.boxadactle.everyslab.datagen.util.LanguageHelper;
import net.minecraft.data.PackOutput;
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
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class NeoLangProvider extends LanguageProvider {

    @FunctionalInterface
    public interface LanguageConstructor {
        LanguageHelper.Provider create(LanguageHelper helper);
    }

    LanguageConstructor constructor;

    public NeoLangProvider(PackOutput output, LanguageConstructor provider, String locale) {
        super(output, EverySlab.MOD_ID, locale);
        this.constructor = provider;
    }

    @Override
    protected void addTranslations() {
        LanguageHelper.Provider provider = constructor.create(new LanguageHelper() {
            @Override
            public void addBlock(Supplier<? extends Block> key, String name) {
                NeoLangProvider.this.addBlock(key, name);
            }

            @Override
            public void add(Block key, String name) {
                NeoLangProvider.this.add(key, name);
            }

            @Override
            public void addItem(Supplier<? extends Item> key, String name) {
                NeoLangProvider.this.addItem(key, name);
            }

            @Override
            public void add(Item key, String name) {
                NeoLangProvider.this.add(key, name);
            }

            @Override
            public void addItemStack(Supplier<ItemStack> key, String name) {
                NeoLangProvider.this.addItem(() -> key.get().getItem(), name);
            }

            @Override
            public void add(ItemStack key, String name) {
                NeoLangProvider.this.add(key.getItem(), name);
            }

            @Override
            public void addEffect(Supplier<? extends MobEffect> key, String name) {
                NeoLangProvider.this.addEffect(key, name);
            }

            @Override
            public void add(MobEffect key, String name) {
                NeoLangProvider.this.add(key, name);
            }

            @Override
            public void addEntityType(Supplier<? extends EntityType<?>> key, String name) {
                NeoLangProvider.this.addEntityType(key, name);
            }

            @Override
            public void add(EntityType<?> key, String name) {
                NeoLangProvider.this.add(key, name);
            }

            @Override
            public void addTag(Supplier<? extends TagKey<?>> key, String name) {
                NeoLangProvider.this.addTag(key, name);
            }

            @Override
            public void add(TagKey<?> tagKey, String name) {
                NeoLangProvider.this.add(tagKey, name);
            }

            @Override
            public void add(String key, String value) {
                NeoLangProvider.this.add(key, value);
            }

            @Override
            public void add(String key, Component value) {
                NeoLangProvider.this.add(key, value);
            }

            @Override
            public void addDimension(ResourceKey<Level> dimension, String value) {
                NeoLangProvider.this.addDimension(dimension, value);
            }

            @Override
            public void addBiome(ResourceKey<Biome> biome, String value) {
                NeoLangProvider.this.addBiome(biome, value);
            }
        });

        provider.addTranslations();
    }
}