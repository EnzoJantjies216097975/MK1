package com.map711s.mk1.data.model

data class Combo(
    val name: String,
    val inputSequence: String,
    val damage: Int,
    val difficulty: DifficultyLevel,
    val description: String,
    val requiredKameo: String? = null, // null if no specific Kameo is required
    val videoUrl: String? = null // Optional URL to a demonstration video
)