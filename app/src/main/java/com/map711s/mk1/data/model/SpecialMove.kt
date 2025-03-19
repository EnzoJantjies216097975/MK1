package com.map711s.mk1.data.model

data class SpecialMove(
    val name: String,
    val inputCommand: String,
    val description: String,
    val requiredKameo: String? = null // null if no specific Kameo is required
)