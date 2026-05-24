package dev.boxadactle.everyslab.neoforge;

import dev.boxadactle.everyslab.Constants;
import dev.boxadactle.everyslab.EverySlab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void register(RegisterEvent event) {
        EverySlab.init();
        event.register(Registries.BLOCK, registry -> {
            EverySlab.FENCE_GATES.registerBlocks(registry::register);
            EverySlab.FENCES.registerBlocks(registry::register);
            EverySlab.SLABS.registerBlocks(registry::register);
            EverySlab.STAIRS.registerBlocks(registry::register);
            EverySlab.WALLS.registerBlocks(registry::register);
        });

        event.register(Registries.ITEM, registry -> {
            EverySlab.FENCE_GATES.registerItems(registry::register);
            EverySlab.FENCES.registerItems(registry::register);
            EverySlab.SLABS.registerItems(registry::register);
            EverySlab.STAIRS.registerItems(registry::register);
            EverySlab.WALLS.registerItems(registry::register);
        });

        event.register(Registries.CREATIVE_MODE_TAB, registry -> {
            registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "fencegates"), CreativeModeTab.builder()
                    .displayItems((params, output) -> {
                        EverySlab.FENCE_GATES.forEachBlock(output::accept);
                    })
                    .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_fence_gate")).get().value()))
                    .title(Component.translatable("itemGroup.everyslab_fencegates"))
                    .build()
            );

            registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "fences"), CreativeModeTab.builder()
                    .displayItems((params, output) -> {
                        EverySlab.FENCES.forEachBlock(output::accept);
                    })
                    .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_fence")).get().value()))
                    .title(Component.translatable("itemGroup.everyslab_fences"))
                    .build()
            );

            registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "slabs"), CreativeModeTab.builder()
                    .displayItems((params, output) -> {
                        EverySlab.SLABS.forEachBlock(output::accept);
                    })
                    .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_slab")).get().value()))
                    .title(Component.translatable("itemGroup.everyslab_slabs"))
                    .build()
            );

            registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "stairs"), CreativeModeTab.builder()
                    .displayItems((params, output) -> {
                        EverySlab.STAIRS.forEachBlock(output::accept);
                    })
                    .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_stairs")).get().value()))
                    .title(Component.translatable("itemGroup.everyslab_stairs"))
                    .build()
            );

            registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "walls"), CreativeModeTab.builder()
                    .displayItems((params, output) -> {
                        EverySlab.WALLS.forEachBlock(output::accept);
                    })
                    .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_wall")).get().value()))
                    .title(Component.translatable("itemGroup.everyslab_walls"))
                    .build()
            );
        });
    }

}
