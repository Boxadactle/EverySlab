# EverySlab

> Adds 1000+ slabs, stairs, walls, fences, and fence gates for almost 300 blocks that were missing them!

![Minecraft](https://img.shields.io/badge/Minecraft-1.20%2B-brightgreen)
![Fabric/Neoforge](https://img.shields.io/badge/Loader-Fabric%20%7C%20Forge%20%7C%20NeoForge-blue)
![License](https://img.shields.io/badge/License-GNU%20GPLv3-purple)
 
---

## What is EverySlab?

Minecraft has always had a frustrating limitation: only a small subset of blocks with slab and stair variants. EverySlab fixes this by automatically generating slab, stair, wall, fence, and fence gate variants for almost every solid block in the game.

No configuration needed. Just drop in the mod and every block gets its variants.

[![asulaq on PlanetMinecraft](https://boxadactle.dev/img/everyslab/build.png)](https://www.planetminecraft.com/project/small-medieval-village-house-6940008/)

---

## Features

### Universal Coverage

![living room build](https://boxadactle.dev/img/everyslab/living_room.png)

Slab and stair variants are generated for every registered vanilla solid block. This includes all stone types, wood types, ores, and more.

### Automatic Recipes

![crafting magma stairs](https://boxadactle.dev/img/everyslab/crafting.png)

Crafting table and stonecutter recipes are created for all new variants. Slabs are crafted from their full block, stairs from slabs, and walls/fences from their full block.

### Inherited Block Properties and Behaviors

![wow redstone](https://boxadactle.dev/img/everyslab/properties.png)

Hardness, blast resistance, tool type, harvest level, sounds, and behaviors are copied from the parent block. As seen in the image, the "Redstone Stairs" activate the piston, and the "Redstone Lamp Slabs" are activated by the redstone block.

### Creative Tab Integration

![creative tabs](https://boxadactle.dev/img/everyslab/creative.png)

Variants appear in designated creative tabs, separated by variant type. For example, all slabs are in the "Slab Variants" tab, while all stairs are in the "Stair Variants" tab.

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

EverySlab does not add variants for other building mods such as like Quark, Supplementaries, or Create.
 
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

If you would like to translate this mod, please follow [this guide](https://github.com/Boxadactle/EverySlab/tree/master/neoforge/src/main/java/dev/boxadactle/everyslab/datagen/localization/).

---

## License

EverySlab is licensed under the [GNU GPLv3 License](https://github.com/Boxadactle/EverySlab/blob/master/LICENSE).
 
---

## Credits

Made with ❤️ by Boxadactle. Inspired by years of wanting to build a diagonal staircase out of sponges.
 
