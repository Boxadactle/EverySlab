package dev.boxadactle.everyslab.fabric;

import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.EverySlab;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabs {

    public static final ResourceKey<CreativeModeTab> FENCEGATE_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Constants.MOD_ID, "fencegates"));
    public static final CreativeModeTab FENCEGATE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_fence_gate")).get().value()))
            .title(Component.translatable("itemGroup.everyslab_fencegates"))
            .build();

    public static final ResourceKey<CreativeModeTab> FENCE_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Constants.MOD_ID, "fences"));
    public static final CreativeModeTab FENCE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_fence")).get().value()))
            .title(Component.translatable("itemGroup.everyslab_fences"))
            .build();

    public static final ResourceKey<CreativeModeTab> SLAB_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Constants.MOD_ID, "slabs"));
    public static final CreativeModeTab SLAB_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_slab")).get().value()))
            .title(Component.translatable("itemGroup.everyslab_slabs"))
            .build();

    public static final ResourceKey<CreativeModeTab> STAIR_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Constants.MOD_ID, "stairs"));
    public static final CreativeModeTab STAIR_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_stairs")).get().value()))
            .title(Component.translatable("itemGroup.everyslab_stairs"))
            .build();

    public static final ResourceKey<CreativeModeTab> WALL_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Constants.MOD_ID, "walls"));
    public static final CreativeModeTab WALL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_wall")).get().value()))
            .title(Component.translatable("itemGroup.everyslab_walls"))
            .build();

    public static void registerAll() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FENCEGATE_GROUP_KEY, FENCEGATE_GROUP);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FENCE_GROUP_KEY, FENCE_GROUP);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SLAB_GROUP_KEY, SLAB_GROUP);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, STAIR_GROUP_KEY, STAIR_GROUP);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, WALL_GROUP_KEY, WALL_GROUP);
    }

    public static void addItems() {
        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.FENCEGATE_GROUP_KEY).register(group -> {
            EverySlab.FENCE_GATES.forEachBlock(group::accept);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.FENCE_GROUP_KEY).register(group -> {
            EverySlab.FENCES.forEachBlock(group::accept);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.SLAB_GROUP_KEY).register(group -> {
            EverySlab.SLABS.forEachBlock(group::accept);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.STAIR_GROUP_KEY).register(group -> {
            EverySlab.STAIRS.forEachBlock(group::accept);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeTabs.WALL_GROUP_KEY).register(group -> {
            EverySlab.WALLS.forEachBlock(group::accept);
        });
    }
}
