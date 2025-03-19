package com.map711s.mk1.data

import com.map711s.mk1.data.model.Combo
import com.map711s.mk1.data.model.Fatality
import com.map711s.mk1.data.model.SpecialMove

object MortalKombatRepository {

    // Get all main characters
    fun getAllCharacters(): List<Character> {
        return listOf(
            Character(
                id = "scorpion",
                name = "Scorpion",
                imageUrl = "https://placeholder.com/scorpion",
                bio = "Hanzo Hasashi, once the leader of the Shirai Ryu clan, was murdered by Sub-Zero. Resurrected as the vengeful specter Scorpion, he now serves as a champion for Earthrealm.",
                difficultyLevel = DifficultyLevel.INTERMEDIATE,
                specialMoves = listOf(
                    SpecialMove(
                        name = "Spear",
                        inputCommand = "Back, Forward, Front Punch",
                        description = "Scorpion throws a kunai attached to a rope, pulling the opponent towards him."
                    ),
                    SpecialMove(
                        name = "Hellfire",
                        inputCommand = "Down, Back, Front Kick",
                        description = "Scorpion breathes fire, damaging the opponent."
                    ),
                    SpecialMove(
                        name = "Teleport Punch",
                        inputCommand = "Down, Back, Front Punch",
                        description = "Scorpion teleports behind the opponent and delivers a punch."
                    )
                ),
                combos = listOf(
                    Combo(
                        name = "Hell Chain",
                        inputSequence = "Back Forward Front Punch, Front Kick, Back Punch, Front Kick",
                        damage = 320,
                        difficulty = DifficultyLevel.INTERMEDIATE,
                        description = "Scorpion uses his spear to start a devastating combo."
                    ),
                    Combo(
                        name = "Infernal Fury",
                        inputSequence = "Front Punch, Front Punch, Back Kick, Down Forward Front Punch",
                        damage = 280,
                        difficulty = DifficultyLevel.ADVANCED,
                        description = "A fast combo ending with a special move.",
                        requiredKameo = "Frost"
                    )
                ),
                fatalities = listOf(
                    Fatality(
                        name = "Who's Next?",
                        inputCommand = "Down, Forward, Down, Back Punch (Close Range)",
                        description = "Scorpion summons hellfire that burns the opponent to a skeleton."
                    ),
                    Fatality(
                        name = "Chain Reaction",
                        inputCommand = "Back, Forward, Back, Front Kick (Mid Range)",
                        description = "Scorpion wraps his chain around the opponent and pulls in multiple directions, dismembering them."
                    )
                )
            ),

            Character(
                id = "sub-zero",
                name = "Sub-Zero",
                imageUrl = "https://placeholder.com/subzero",
                bio = "Kuai Liang took up the mantle of Sub-Zero after his brother's death. As Grandmaster of the Lin Kuei, he seeks to restore honor to his clan through the defense of Earthrealm.",
                difficultyLevel = DifficultyLevel.BEGINNER,
                specialMoves = listOf(
                    SpecialMove(
                        name = "Ice Ball",
                        inputCommand = "Down, Forward, Front Punch",
                        description = "Sub-Zero sends a ball of ice that freezes the opponent."
                    ),
                    SpecialMove(
                        name = "Slide",
                        inputCommand = "Back, Down, Front Kick",
                        description = "Sub-Zero slides across the ground, knocking down the opponent."
                    ),
                    SpecialMove(
                        name = "Ice Clone",
                        inputCommand = "Down, Back, Front Punch",
                        description = "Sub-Zero creates a clone of himself made of ice. If the opponent touches it, they freeze.",
                        requiredKameo = "Cyrax"
                    )
                ),
                combos = listOf(
                    Combo(
                        name = "Frozen Punishment",
                        inputSequence = "Down Forward Front Punch, Front Punch, Back Punch, Front Kick",
                        damage = 290,
                        difficulty = DifficultyLevel.BEGINNER,
                        description = "Sub-Zero freezes the opponent and follows with a series of strikes."
                    ),
                    Combo(
                        name = "Lin Kuei Legacy",
                        inputSequence = "Back Punch, Front Punch, Down Back Front Kick, Down Forward Back Punch",
                        damage = 350,
                        difficulty = DifficultyLevel.EXPERT,
                        description = "An advanced combo utilizing multiple special moves."
                    )
                ),
                fatalities = listOf(
                    Fatality(
                        name = "Spine Rip",
                        inputCommand = "Forward, Down, Forward, Back Punch (Close Range)",
                        description = "Sub-Zero grabs the opponent's head and rips out their spine with the skull still attached."
                    ),
                    Fatality(
                        name = "Ice Shatter",
                        inputCommand = "Back, Forward, Down, Front Kick (Any Range)",
                        description = "Sub-Zero freezes the opponent solid, then shatters them into tiny pieces."
                    )
                )
            ),

            // You can add more characters following the same pattern
        )
    }

    // Get all Kameo characters
    fun getAllKameoCharacters(): List<KameoCharacter> {
        return listOf(
            KameoCharacter(
                id = "cyrax",
                name = "Cyrax",
                imageUrl = "https://placeholder.com/cyrax",
                bio = "Once a human warrior of the Lin Kuei, Cyrax was transformed into a cyborg. He now serves as a Kameo fighter, offering technical support in battle.",
                specialMoves = listOf(
                    SpecialMove(
                        name = "Net Trap",
                        inputCommand = "Down, Back, Kameo Button",
                        description = "Cyrax fires a net that traps the opponent, setting them up for combos."
                    ),
                    SpecialMove(
                        name = "Bomb Drop",
                        inputCommand = "Down, Forward, Kameo Button",
                        description = "Cyrax drops bombs that explode after a short delay."
                    )
                )
            ),

            KameoCharacter(
                id = "frost",
                name = "Frost",
                imageUrl = "https://placeholder.com/frost",
                bio = "A former Lin Kuei warrior and Sub-Zero's apprentice, Frost assists in combat with her cryomancer abilities.",
                specialMoves = listOf(
                    SpecialMove(
                        name = "Ice Daggers",
                        inputCommand = "Back, Forward, Kameo Button",
                        description = "Frost throws ice daggers at the opponent."
                    ),
                    SpecialMove(
                        name = "Cryo Storm",
                        inputCommand = "Down, Down, Kameo Button",
                        description = "Frost creates a storm of ice that damages and slows the opponent."
                    )
                )
            )

            // You can add more Kameo characters following the same pattern
        )
    }

    // Get a specific character by ID
    fun getCharacterById(id: String): Character? {
        return getAllCharacters().find { it.id == id }
    }

    // Get a specific Kameo character by ID
    fun getKameoCharacterById(id: String): KameoCharacter? {
        return getAllKameoCharacters().find { it.id == id }
    }

    // Get combos that require a specific Kameo character
    fun getCombosForKameo(kameoId: String): List<Pair<Character, Combo>> {
        val result = mutableListOf<Pair<Character, Combo>>()

        getAllCharacters().forEach { character ->
            character.combos
                .filter { it.requiredKameo == kameoId }
                .forEach { combo ->
                    result.add(Pair(character, combo))
                }
        }

        return result
    }
}