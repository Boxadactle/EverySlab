# EverySlab

> Adds 1000+ slabs, stairs, walls, fences, and fence gates for almost 300 blocks that were missing them!

![Minecraft](https://img.shields.io/badge/Minecraft-1.20%2B-brightgreen)
![Fabric/Neoforge](https://img.shields.io/badge/Loader-Fabric%20%7C%20Forge%20%7C%20NeoForge-blue)
![License](https://img.shields.io/badge/License-GNU%20GPLv3-purple)
 
---

## What is EverySlab?

Minecraft has always had a frustrating limitation: only a small subset of blocks with slab and stair variants. EverySlab fixes this by automatically generating slab, stair, wall, fence, and fence gate variants for almost every solid block in the game.

No configuration needed. Just drop in the mod and every block gets its variants.
 
---

## Features

- **Universal coverage** — slab and stair variants are generated for every registered solid block
- **Automatic recipes** — crafting table and stonecutter recipes are created for all new variants
- **Inherited block properties** — hardness, blast resistance, tool type, harvest level, and sounds are copied from the parent block
- **Creative tab integration** — variants appear in designated creative tabs
- **Duplicate prevention** — blocks that already have official slab/stair variants are skipped automatically
- **Opt-out config** — blacklist specific blocks or entire namespaces via the config file

---

## Installation

1. Install your mod loader of choice (Fabric or NeoForge)
2. If using Fabric, also install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download the latest EverySlab `.jar`
4. Place the `.jar` in your `mods/` folder
5. Launch the game — no further setup required

---

## Compatibility

| Loader   | Status     | Notes                          |
|----------|------------|-------------------------------|
| Fabric   | ✅ Supported | Requires Fabric API            |
| Quilt    | ✅ Supported | Requires QSL                   |
| NeoForge | ✅ Supported | No extra dependencies          |

EverySlab is fully compatible with other building and decoration mods. Blocks added by mods like Quark, Supplementaries, or Create will not receive variants.
 
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

The compiled `.jar` will be in `build/<loader>/libs/`.
 
---

## Contributing

Contributions are welcome! Please open an issue before submitting a pull request for large changes. Bug reports and feature requests can be filed via [GitHub Issues](https://github.com/Boxadactle/EverySlab/issues).

### Translating

As EverySlab's assets are generated when built, mod translation is not as straightfoward as other mods.

If you would like to translate this mod, please follow [this guide](https://github.com/Boxadactle/EverySlab/tree/master/neoforge/src/main/java/dev/boxadactle/everyslab/datagen/localization/README.md).

---

## License

EverySlab is licensed under the [GNU GPLv3 License](https://github.com/Boxadactle/EverySlab/blob/master/LICENSE).
 
---

## Credits

Made with ❤️ by Boxadactle. Inspired by years of wanting to build a diagonal staircase out of sponges.
 