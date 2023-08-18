## 3.0.1
* Removed forge dependency line from the mods.toml and marked as Forge and NeoForge for CF.
  * The dependency will be added back and the Forge marker will be removed once CF supports Neo correctly.

## 3.0.0
* Updated to 1.20.1

## 2.0.1
* Switch mechanism to use Entity#tickCount instead of recording the time of spawn and using the difference.
  * Should allow the mod to work on entities that spawn without posting SpecialSpawn (most entities, due to forge issues).

## 2.0.0
* Updated to 1.19.x

## 1.0.0
* Initial Release