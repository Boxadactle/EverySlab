package dev.boxadactle.everyslab;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ModConfig {

    private static CommentedFileConfig config;

    // Cached values
    private static List<String> blockBlacklist;
    private static boolean generateFenceGates;
    private static boolean generateFences;
    private static boolean generateSlabs;
    private static boolean generateStairs;
    private static boolean generateWalls;

    public static void load() {
        Path configPath = Paths.get(Constants.CONFIG_PATH.toString(), "everyslab.toml");

        config = CommentedFileConfig.builder(configPath)
                .preserveInsertionOrder()
                .build();

        // Write defaults if the file doesn't exist yet
        if (!configPath.toFile().exists()) {
            config.set("block_blacklist", new ArrayList<String>());
            config.setComment("block_blacklist",
                    "Blacklist specific block IDs from receiving variants\n" +
                            "Bedrock and Barriers are automatically blacklist\n" +
                            "Example: [\"minecraft:bedrock\", \"minecraft:barrier\"]");

            config.set("generate_fence_gates", true);
            config.setComment("generate_fence_gates",
                    "Generate fence gate variants (default: true)");

            config.set("generate_fences", true);
            config.setComment("generate_fences",
                    "Generate fence variants (default: true)");

            config.set("generate_slabs", true);
            config.setComment("generate_slabs",
                    "Generate slab variants (default: true)");

            config.set("generate_stairs", true);
            config.setComment("generate_stairs",
                    "Generate stair variants (default: true)");

            config.set("generate_walls", true);
            config.setComment("generate_walls",
                    " Generate wall variants (default: true)");

            config.save();
        } else config.load();

        cache();
    }

    private static void cache() {
        blockBlacklist     = config.get("block_blacklist");
        generateFenceGates = config.getOrElse("generate_fence_gates", true);
        generateFences     = config.getOrElse("generate_fences", true);
        generateSlabs      = config.getOrElse("generate_slabs", true);
        generateStairs     = config.getOrElse("generate_stairs", true);
        generateWalls      = config.getOrElse("generate_walls", true);
    }

    // --- Accessors ---

    public static List<String> getBlockBlacklist()         { return blockBlacklist; }
    public static boolean shouldGenerateFenceGates()       { return generateFenceGates; }
    public static boolean shouldGenerateFences()           { return generateFences; }
    public static boolean shouldGenerateSlabs()            { return generateSlabs; }
    public static boolean shouldGenerateStairs()           { return generateStairs; }
    public static boolean shouldGenerateWalls()            { return generateWalls; }
}