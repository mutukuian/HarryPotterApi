package com.example.retro.data.remote.network

import com.example.retro.core.common.Constants.END_POINT
import com.example.retro.data.remote.dto.CharactersListDto
import retrofit2.http.GET

interface Api {

    @GET(END_POINT)
    suspend fun getCharactersList():List<CharactersListDto>
}