package com.example.retro.data.remote.dto

import com.example.retro.domain.model.CharactersList

data class CharactersListDto(
    val actor: String,
    val alive: Boolean,
    val alternate_actors: List<Any>,
    val alternate_names: List<String>,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: String
)

fun CharactersListDto.toCharactersList(): CharactersList {
    return CharactersList(
        id, image, name, actor, alive, gender, yearOfBirth
    )
}