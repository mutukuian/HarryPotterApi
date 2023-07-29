package com.example.retro.presentation.states

import com.example.retro.domain.model.CharactersList

data class CharactersListState(
    val isLoading: Boolean = false,
    val characters:List<CharactersList> = emptyList(),
    val error: String = ""
)
