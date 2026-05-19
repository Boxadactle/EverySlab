# EverySlab

> Adds 1000+ slabs, stairs, walls, fences, and fence gates for almost **every block** in the game — vanilla and beyond.

![Minecraft](https://img.shields.io/badge/Minecraft-1.20%2B-brightgreen)
![Fabric/Neoforge](https://img.shields.io/badge/Loader-Fabric%20%7C%20Forge%20%7C%20NeoForge-blue)
![License](https://img.shields.io/badge/License-MIT-purple)
 
---

## What is EverySlab?

Minecraft has always had a frustrating limitation: only a small subset of blocks with slab and stair variants. EverySlab fixes this by automatically generating slab, stair, wall, fence, and fence gate variants for almost every solid block in the game.

No configuration needed. Just drop in the mod and every block gets its variants.
 
---

## Features

- **Universal coverage** — slab and stair variants are generated for every registered block, including modded ones
- **Automatic recipes** — crafting table and stonecutter recipes are created for all new variants
- **Inherited block properties** — hardness, blast resistance, tool type, harvest level, and sounds are copied from the parent block
- **Creative tab integration** — variants appear alongside their parent blocks in the creative inventory
- **Duplicate prevention** — blocks that already have official slab/stair variants are skipped automatically
- **Opt-out config** — blacklist specific blocks or entire namespaces via the config file

---

## Installation

1. Install your mod loader of choice (Fabric, Forge, or NeoForge)
2. If using Fabric, also install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download the latest EverySlab `.jar` from the [Releases](#) page
4. Place the `.jar` in your `mods/` folder
5. Launch the game — no further setup required

---

## Compatibility

| Loader     | Status     | Notes                          |
|------------|------------|-------------------------------|
| Fabric     | ✅ Supported | Requires Fabric API            |
| Quilt      | ✅ Supported | Requires QSL                   |
| Forge      | ✅ Supported | No extra dependencies          |

EverySlab is fully compatible with other building and decoration mods. Blocks added by mods like Quark, Supplementaries, or Create will automatically receive slab and stair variants as well. Existing official variants from those mods are detected and skipped.
 
---

## Configuration

A config file is generated at `config/everyslab.toml` on first launch.

```toml
#Blacklist specific block IDs from receiving variants
#Bedrock and Barriers are automatically blacklist
#Example: ["minecraft:bedrock", "minecraft:barrier"]
block_blacklist = []

#Generate fence gate variants (default: true)
generate_fence_gates = false

#Generate fence variants (default: true)
generate_fences = false

#Generate slab variants (default: true)
generate_slabs = true

#Generate stair variants (default: true)
generate_stairs = true

# Generate wall variants (default: true)
generate_walls = false

```
 
---

## Building from Source

```bash
git clone https://github.com/Boxadactle/EverySlab.git
cd EverySlab
./gradlew build
```

The compiled `.jar` will be in `build/libs/`.
 
---

## Contributing

Contributions are welcome! Please open an issue before submitting a pull request for large changes. Bug reports and feature requests can be filed via [GitHub Issues](#).
 
---

## License

EverySlab is licensed under the [MIT License](LICENSE).
 
---

## Credits

Made with ❤️ by [Your Name]. Inspired by years of wanting to build a diagonal staircase out of deepslate bricks.
 