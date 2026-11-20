# Changelog

## 1.3.1

### Fixes
- **Fabric:** the Crucible Hornshield and Erdtree Greatshield now lose durability when blocking and are disabled by axes, like the vanilla shield. NeoForge already did this.

## 1.3.0

Includes everything since 1.2.3.

### New
- **Blood Loss:** the Nagakiba now builds up Blood Loss on every hit. The 4th hit within 5 seconds bursts for 12% of the target's max health (3 to 30 damage), ignoring armor.
- **New full-set bonuses:**
  - **Night's Cavalry:** +20% movement speed, plus Night Vision at night.
  - **Blaidd:** +3 attack damage.
  - **Maliketh:** +6 attack damage.
  - **Ronin:** blocking an attack with a shield grants Strength for 4 seconds.
  - **Radahn:** Gravity Crash. Landing from a fall of 4+ blocks sends out a shockwave that damages and launches nearby enemies.
- **Shield traits:**
  - **Crucible Hornshield:** blocking a melee attack throws the attacker back.
  - **Erdtree Greatshield:** blocking grants brief Absorption.
- **Advancements:** a new Tarnished Legacy tab with 7 advancements, including two challenges.
- **Death messages** for Blood Loss, Gravity Crash and the Greatsword of Radahn's pillars of light.
- **Better Combat:** the Royal Greatsword and Maliketh's Black Blade now have attack animations. All four heavy swords use two-handed claymore moves.
- Added the `c:tools/shield` and `c:tools/melee_weapon` tags, so other mods recognise the weapons and shields.

### Changes
- Speed and attack set bonuses are now attribute modifiers instead of potion effects. Milk no longer removes them, and they don't clash with potions.
- **Tree Sentinel:** the set's Absorption now renews every 10 seconds instead of every second.
- **Armor durability** now scales by slot like vanilla armor. Chestplates last longer and helmets less.
- **Greatsword of Radahn:** the cooldown after an abandoned combo now shows on the item.
- Gravity Crash and the Greatsword of Radahn's light no longer break armor stands. Blood Loss bursts don't knock the target back.
- New mod icon.

### Fixes
- **Crucible Greatsword:** "Primordial Force" pulled enemies towards you instead of knocking them away.
- **Greatsword of Radahn:**
  - The skill could stay on cooldown for a very long time after switching single-player worlds.
  - The pillars of light could get stuck if you dropped the sword mid-combo.
  - The skill no longer hits your own pets.
  - Fast combos dealt far less damage than slow ones.
- **Capes:**
  - In multiplayer, every Tree Sentinel cape moved in sync.
  - Capes moved faster at higher frame rates.
- **Crucible Tree armor** textures could fail to load in some modpacks.
- **Crucible Axe and Crucible Tree armor:** with mods that cache armor rendering, the two sets could show each other's textures.
- **Crucible Axe armor:** the model had a generic name that could clash with other mods' models.
- Set bonuses now turn off reliably when armor is removed, dropped or broken.
- **NeoForge:** the mod no longer tries to load on Minecraft versions other than 1.21.1.
