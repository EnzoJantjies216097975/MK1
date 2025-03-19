package com.map711s.mk1.data.model

data class Fatality(
    val name: String,
    val inputCommand: String,
    val description: String,
    val unlockRequirement: String? = null
)