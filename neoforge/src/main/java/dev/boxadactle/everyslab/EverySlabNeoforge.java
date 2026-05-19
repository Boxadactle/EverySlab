package dev.boxadactle.everyslab;

import dev.boxadactle.everyslab.datagen.*;
import dev.boxadactle.everyslab.registry.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.List;
import java.util.Set;

@Mod(Constants.MOD_ID)
public class EverySlabNeoforge {

    public EverySlabNeoforge(IEventBus eventBus) {
        Constants.IS_DEV = !FMLEnvironment.production;
        Constants.CONFIG_PATH = FMLPaths.CONFIGDIR.get();
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void gatherData(GatherDataEvent.Client event) {
            event.createProvider(EnUsProvider::new);
            event.createProvider(ModelGenerator::new);
            event.createProvider(BlockTagsGenerator::new);
            event.createProvider((output, lookupProvider) -> new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(
                    LootTableGenerator::new,
                    LootContextParamSets.BLOCK
            )), lookupProvider));
            event.createProvider(RecipeGenerator.Runner::new);
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void register(RegisterEvent event) {
            EverySlab.init();
            event.register(Registries.BLOCK, registry -> {
                if (ModConfig.shouldGenerateFenceGates()) EverySlab.FENCE_GATES.registerBlocks(registry::register);
                if (ModConfig.shouldGenerateFences()) EverySlab.FENCES.registerBlocks(registry::register);
                if (ModConfig.shouldGenerateSlabs()) EverySlab.SLABS.registerBlocks(registry::register);
                if (ModConfig.shouldGenerateStairs()) EverySlab.STAIRS.registerBlocks(registry::register);
                if (ModConfig.shouldGenerateWalls()) EverySlab.WALLS.registerBlocks(registry::register);
            });

            event.register(Registries.ITEM, registry -> {
                if (ModConfig.shouldGenerateFenceGates()) EverySlab.FENCE_GATES.registerItems(registry::register);
                if (ModConfig.shouldGenerateFences()) EverySlab.FENCES.registerItems(registry::register);
                if (ModConfig.shouldGenerateSlabs()) EverySlab.SLABS.registerItems(registry::register);
                if (ModConfig.shouldGenerateStairs()) EverySlab.STAIRS.registerItems(registry::register);
                if (ModConfig.shouldGenerateWalls()) EverySlab.WALLS.registerItems(registry::register);
            });

            event.register(Registries.CREATIVE_MODE_TAB, registry -> {
                if (ModConfig.shouldGenerateFenceGates()) registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "fencegates"), CreativeModeTab.builder()
                        .displayItems((params, output) -> {
                            EverySlab.FENCE_GATES.forEachBlock(output::accept);
                        })
                        .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_fence_gate")).get().value()))
                        .title(Component.translatable("itemGroup.everyslab_fencegates"))
                        .build()
                );

                if (ModConfig.shouldGenerateFences()) registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "fences"), CreativeModeTab.builder()
                        .displayItems((params, output) -> {
                            EverySlab.FENCES.forEachBlock(output::accept);
                        })
                        .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_fence")).get().value()))
                        .title(Component.translatable("itemGroup.everyslab_fences"))
                        .build()
                );

                if (ModConfig.shouldGenerateSlabs()) registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "slabs"), CreativeModeTab.builder()
                        .displayItems((params, output) -> {
                            EverySlab.SLABS.forEachBlock(output::accept);
                        })
                        .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_slab")).get().value()))
                        .title(Component.translatable("itemGroup.everyslab_slabs"))
                        .build()
                );

                if (ModConfig.shouldGenerateStairs()) registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "stairs"), CreativeModeTab.builder()
                        .displayItems((params, output) -> {
                            EverySlab.STAIRS.forEachBlock(output::accept);
                        })
                        .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "diamond_block_stairs")).get().value()))
                        .title(Component.translatable("itemGroup.everyslab_stairs"))
                        .build()
                );

                if (ModConfig.shouldGenerateWalls()) registry.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "walls"), CreativeModeTab.builder()
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
}