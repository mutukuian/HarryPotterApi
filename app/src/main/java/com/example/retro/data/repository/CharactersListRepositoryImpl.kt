package com.example.retro.data.repository

import com.example.retro.data.remote.dto.CharactersListDto
import com.example.retro.data.remote.network.Api
import com.example.retro.domain.repository.CharactersListRepository
import javax.inject.Inject

class CharactersListRepositoryImpl @Inject constructor(
    private val api:Api
):CharactersListRepository{
    override suspend fun getCharactersList(): List<CharactersListDto> {
        return api.getCharactersList()
    }
}