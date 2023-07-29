package com.example.retro.domain.model

data class CharactersList(
    val id: String,
    val image: String,
    val name: String,
    val actor: String,
    val alive: Boolean,
    val gender: String,
    val yearOfBirth: String?
)
