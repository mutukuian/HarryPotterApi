package com.example.retro.domain.repository

import com.example.retro.data.remote.dto.CharactersListDto

interface CharactersListRepository {

    suspend fun getCharactersList():List<CharactersListDto>
}