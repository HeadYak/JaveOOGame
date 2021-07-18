* Drops involving exp, items and gold are randomised from set values e.g. killing a slug will give u randomised amount of exp between 4-5 likewise with gold etc

* Cards can be obtained from the shop or as drops, when oldest card is replaced due to having too many cards the player will receive drops according to the randomness set above


* World generation: At the start of the game, a randomised path is created as the world. The game is immediately in a pause state and starts once the player places the hero’s castle

* Battle: When a battle is initiated, the game enters a combat state (which can also be paused) and an overlay depicting a representation of the battle is shown. Each side of the battle takes turns dealing damage until one side is defeated, and the game continues looping.

* The game can be saved at any point in time and loaded to play later.

* Equipments character has equipped: The character can only equip one of each item type (weapon, armour, shields and helmets)

* The shop only contains three items

* Buffs from campfires do not stack, as does support from support buildings

* Buildings that can spawn enemies spawn them on the closest path tile

* Ally units do not appear on paths etc but appear like items and will only be used in conflict

* The character can only have 3 allies at once

* buffs and support are only applied after the character moves

* Slugs randomly move one tile in any direction per tick

* Zombies move one tile per two ticks only when in range of a character (5 tiles)

* When character enters the range of zombie, it moves once immediately and proceeds to move every two turns

* If character escapes from zombie range zombie goes back to waiting

* Vampires move two tiles per tick

* Vampires will always run in the opposite direction of the character initially, but if it enters the range of a campfire it will change direction
Assumptions: Combat

* Enemies in combat as support units are not required to die for the battle to end

* Support units will deal 0.5x dmg

* Character's isBuffed (in range of campfire): x2 atk

* Character's isSupported (in range of tower): tower deals dmg

* When enemies attack the character, there is a 10% chance enemy will only damage an ally, 40% chance the enemy will hit both and 50% chance it hits only the character