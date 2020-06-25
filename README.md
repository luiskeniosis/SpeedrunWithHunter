# Speedrun With Hunter
SpEedRUn with HuNteR???!??!🤪 **Note: I will move this into the Wiki section once I finialize the project a little more.**

## Concept:
*(Ideas that I had when I began making this game mode. Tweaked as I worked on the project)*  
Players are split into teams of hunters and runners in a 1:1or2 ratio.
This means that if there are 2 players, 1 is a hunter, and 1 is a runner.
If there are 3 players, 1 is hunter, 2 are runners
If there are 4 players, 2 are hunters, 2 are runners… etc
The goal of the runner(s) is to speed run the game, and beat the ender dragon. The goal of the hunter(s) is to kill the runner(s).

Hunters have a tracking compass that will track the nearest runner. Hunters have infinite respawns, but will drop everything in their central inventory on death. This excludes equipped armor, main hand, and offhand.

Runners only have one life. However, they have speed 1 when moving, and wooden and stone tools they craft have efficiency 1 applied automatically.

Runners kits are refered to as professions, which helps them gather resources, or help them escape.

Hunters kits are refered to as just Hunters, that help them catch runners, kill them, or to make it harder for the runner to beat the game.

Portals used in the overworld are partially destroyed once it's been used. Nether portals will lose 3 out of the 10 frame blocks, and end portals will lose 3 out of the 12 eyes that were placed. This is to give runners a bit of time after getting to the nether/end to do what they need to do there.

There is no grace period when the game starts. All players are randomly spread in a 75 block RADIUS, meaning they can spawn on the same block, or up to 150 blocks away. All up to luck. Runners are given a burst of speed, haste, and dolphin's grace when they first walk.

If a single runner enters the end, hunters in the nether and the overworld will gain a huge speed boost until they enter the end as well.

## Lobby System:
When players first join, they spawn in the naturally generated map the game will take place in. They’re given an info book, and the ready wool.
Once all players are ready, teams are randomly assigned (can be changed manually by an admin), and players are given kit selectors. Those on the runners team will get to choose a profession, those on the hunters team will get to choose a hunter to play as.
After 2 minutes, or after all players have chosen a kit. The game starts.

## Tracking:
Hunters will always have a tracking compass, even after death. It will point to the nearest runner in the current dimension as the hunter that is tracking.

When the hunter is in the overworld: if there are no runners in the overworld, but there is at least 1 runner in the nether, the compass will change its tracking to the nearest nether portal. If there are no runners in the overworld nor the nether, the compass will change its tracking to the nearest end portal.

When the hunter is in the nether: if there are no runners in the nether, the compass will change its tracking to the nether portal that was last used in the nether, as well as display the portal’s coordinates.

When the hunter is in the end: if there are no runners in the end, the hunter is either too early, or too late. The compass will stop tracking in the end either way. This means that in the End, hunters can not track runners, regardless of where any runner is.

## Empowered Abilities:
Each hunter becomes empowered through gameplay. Every hunter will be empowered by 30 minutes, but can be sped up by doing kit specific actions.

## Custom Recipes (Runners Only):
Runners have access to custom recipes to help defend against various hunters, or to help them survive.
### Garlic Coated Arrows
**Recipe:** Arrows and Allium.
Will poison and slow Vampires.

### Silver + Silver Tools
Silver ingots can be crafted with iron ingots and gunpowder. Which can be used to create Silver swords, or silver arrows which deal double damage to werewolves and also sets them on fire.

### Eve's Blessing
-Apple with bone meal creates 2 apples.  
-Golden apples can be crafted with just 4 gold ingots rather than 9.

### Fortress Locator
| **Recipe:**    |                |             |
|    :----:      |    :----:      |    :----:   |
| Glowstone      | Iron Ingot     | Glowstone   |
| Iron Ingot     | Magma Block    | Iron Ingot  |
| Glowstone      | Iron Ingot     | Glowstone   |

Can only be used in the nether, will break after once use, will give the coordinates of the nearest Nether fortress.

## Custom Enchants:
*Heavy WIP*
Custom enchantment books can be crafted by either hunter or runner. When crafted they'll give an enchanted book, which can be applied to its perspective tool by click and drop.
### Autosmelt
| **Recipe:**|                |             |
|    :----:  |    :----:      |    :----:   |
| Redstone   | Redstone Block | Redstone    |
| Furnace    | Book           | Furnace     |
| Coal Block | Coal Block     | Coal Block  |

Can only be applied to pickaxes, automatically smelts ores mined. Overrides silk touch and fortune.

## In the Nether..
Players will have the coordinates of the portal they came through on a sidebar. They have resistance 1 if set on fire until they’re no longer on fire. Runners are given 5 absorption hearts when they first enter the nether

## In the End..
Runners will have constant regeneration and strength, fall damage will be reduced by 60%. Runners will gain 2 respawns per hunter +1, hunters will gain 1 respawn per runner +1 (Ex. in a 1v1, runner will have 3 respawns, hunter will have 2). Dragon will take 40% less damage. Runners are given 10 absorption hearts when they first enter the end.

## Scoreboard
There will be a scoreboard on the side of player's screens (Can be toggled with /s). It will show relevant information for both runners and hunters.
### Universal Scoreboard Items:
Selected Kit, Current Position, Nether Portal Position (In Nether), End Portal Position (Once found)
### Runner Specific Items:
None.. yet
### Hunter Specific Items:
Empowerment Level,

## Runner Kits (Professions):
### Exile
Start the game 200-300 blocks away.

### Warper
Start the game with 5 ender pearls. When thrown, you ride the pearl until it lands. When it lands, it creates a platform under you so you’re safe no matter where you landed.

### Lumberjack
Start with a wood axe, when chopping a tree (with any axe), every log connected to it also breaks. 10% chance of leaves dropping golden apples.

### Miner
Start with a wooden pickaxe, when mining ores (with any pickaxe), every ore connected to the one you broke also breaks.

### Builder
Grants access to custom crafting recipes for the Wall and Tower block.
The wall block is crafted by filling a 3x3 grid with any plank variant, or cobblestone. When placed, it instantly creates a 3x3 wall on top of the block it’s placed on in the direction you’re facing.
The tower block is crafted by a 3 tall vertical line of any plank variant or cobblestone. When placed, it instantly creates a 3 tall tower on top of the block it’s placed on.
As a builder, placing either the Wall or the Tower block directly underneath you will send you to the top of it.

### Swimmer
Grants you dolphin’s grace as long as you’re in water. Grants decaying speed for a few seconds after leaving water as well.

### Survivor
Seeing any player on the hunters team grants you a decaying burst of speed. (Medium cooldown). Dropping below 3 hearts grants a rapid burst of regeneration, healing 3 hearts (Long cooldown).

### Scavenger
Mob drops are doubled, and are instantly placed in your inventory if there is space.

### Backpacker
Get a portable backpack for storage.

### Acrobat
Fall damage is reduced by half.

### Chef
Portable furnace.

## Hunters:
### The Vampire
-During the day: Weakness 1, restores 1 heart per hit when hitting players, restores 0.5 hearts per hit when hitting mobs
-During the night (or in nether/end): Night vision, speed 3, restores 2 hearts per hit when hitting players, restores 1 heart per hit when hitting mobs.
-Vampiric Transformation: Right click kit item to transform into a bat. As a bat, you can fly, reduced max hp (6 heart), you have a smaller hit box, but can’t deal any damage. After 10 seconds, or manually ending, you’re transformed back into a player, and briefly slowed. (Medium cooldown)
#### EMPOWERED: [Speed up empowerment by overhealing]
-Speed 4 permanently.
-Transformation into a bat gains additional effects. Switching into a bat will summon a cloud of bats and apply a blinding effect to all runners in an area around you.
-Transforming back into a player will also summon a cloud of bats which will wither nearby players briefly, and heal you for the damage dealt. Changing back into a player will still briefly slow you but you’ll be invisible.
-Healing received is doubled.
#### WEAKNESS:
-Damage you take from wooden swords is doubled.
-Garlic coated arrows (Allium) will poison and slow you.
-Your max hp is reduced by 2 hearts if in direct sunlight


### The Werewolf
-Eating raw food (rotten flesh, chicken) will never poison your hunger.
-During the day: Hunger 2, hitting a player restores 2 hunger bars, hitting a mob restores 1.
-During the night (or in nether/end):Night Vision, Speed 1, Jump Boost 2, Hunger 4, hitting a player restores 3 hunger bars. Damage is increased by 25%
-Devour: Right click passive mobs to consume them. Killing them instantly (without drops), restoring your hunger by 4 bars, and granting 50% increased dmg for 5 seconds (this effect’s duration is stackable). (2 second cooldown)
-When runners around you drop below 5 hearts, they appear glowing to you, making it harder for them to hide.
In either day or night, appropriate saturation is also restored when hunger is.
#### EMPOWERED: [Speed up empowerment by over saturating hunger]
-Damage is increased by 50%
-You’re able to Devour hostile mobs as well, restoring 6 hunger bars and temporarily increasing damage by 75% for 5 seconds (duration stackable, 2 second cooldown).
#### WEAKNESS:
-Entering the end will start increasing your hunger slowly, starting at hunger 5, up to hunger 10.
-Running out of hunger removes all buffs on you, and slows you.
-Silver tipped arrows and silver swords will burn and slow you.


### The Statue
Constant strength 2 and speed 2.
#### WEAKNESS:
-You will be unable to move as long as any runner has direct sight of you, but you will gain resistance 1. You’ll still be able to turn and attack, only movement and block placing is stopped.
-Pickaxes deal its sword equivalent damage to you plus half a heart.
#### EMPOWERED: [Speed up empowerment by looking at runners, or being looked at]
-Strength increased to 3, speed increased to 3
-When a runner is looking at you, you will only be slowed instead of being unable to move. The slow potency slowly increases if the runner continues to look at you, however once the runner stares at you longer than 10 seconds, they receive the blindness effect for 3 seconds.
-You cannot be slowed or stopped by a runner looking at you if the runner has blindness

### The Huntsman
-Start with an unbreakable crossbow enchanted with piercing and 3 arrows.
-Killing chickens has a 100% chance of dropping 2 feathers.
-Arrows must be crafted with an iron ingot instead of flint, but you will receive 6 arrows when crafting instead of 4.
-After 10 minutes, gain 1 tracking hound, and another one every 5 minutes
-When summoned in, the hounds have a huge burst of speed. If a hound hits a runner, they’re temporarily slowed and are given the glowing effect.
-Killing an entity with your crossbow will make that entity drop a iron bolt as a mob drop
#### WEAKNESS:
-Heavily slowed while holding the crossbow.
-You can’t pick up your used arrows, nor can you use arrows found naturally (skeleton drops). You can only use the arrows you craft with iron ingots.
-Permanent weakness 1
#### EMPOWERED: [Speed up empowerment by killing mobs with the crossbow]
-Your crossbow gains multishot and notches almost immediately. When a runner is hit by a bolt, they are severely slowed, and given the glowing effect for a while.

### The Spectre
-Spectral Warp: Enter the spectral realm, going invisible for 2 minutes, leaving footprints behind wherever you go if you’re sprinting.
-You can’t hit anything, place or break blocks, but you can interact with inventories -including player inventories.
-Right clicking a player will allow you to take 1 item from anywhere in their inventory except hotbar/armor, taking an item will alert the player that a spectre is nearby (Medium cooldown)
-After the time runs out, or by activating again or if you’re damaged by anything, you are frozen for a bit while returning to the real world, playing a sound to alert anyone near you. (Long cooldown)
#### WEAKNESS:
-Exiting the spectral realm grants you weakness, slowness and mining fatigue for 25 seconds. You will briefly have nausea and glowing.
####EMPOWERED: [Speed up empowerment by spending time in the spectral realm]
-You may enter and exit the spectral realm freely with no penalty.
-There is no time limit in how long you can stay in the spectral realm, but once you leave, the cool down is applied.
-You have speed 2 while moving through the spectral realm
-You can attack once while invisible, that attack will deal 50% more damage, and will take you out of the spectral realm.

### Ideas
#### The Arachnoid
-You can craft near invisible wire traps, which when stepped on by a player will apply the glowing effect on them, heavily slow them, and show you their exact coordinates where they sprung the trap.
#### The Leviathan
-Permanent water breathing.
-You gain strength 1 in water.
-Getting a runner below half health grants you swimming speed while you have direct sight of the runner
#### The Demolisher
Ability to charge and break through walls, or smash downwards to dig through the earth
###The Grappler
Gets a grappling hook that you can used to pull yourself along terrain, or to pull players closer to you
#### The Mage?
Double xp, xp used as mana bar. Various spells that cost “mana”?
#### The Chemist?
Easy potion crafting? Random potions?
#### The Hellhound?
Fire resistance, runners glow if they’re under half health, speed in lava, spreads fire while sprinting?
#### The Nymph?
Can call tides to break boats, grow patches of vines to slow runners, or trees to block a path
#### The Yeti?
????
