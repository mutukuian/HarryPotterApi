package com.example.retro.domain.use_case.get_characters_List

import com.example.retro.core.common.Resource
import com.example.retro.data.remote.dto.toCharactersList
import com.example.retro.domain.model.CharactersList
import com.example.retro.domain.repository.CharactersListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val repository: CharactersListRepository
) {
    operator fun invoke():Flow<Resource<List<CharactersList>>> = flow {
        try {
            emit(Resource.Loading<List<CharactersList>>())
            val characters = repository.getCharactersList().map { it.toCharactersList() }
            emit(Resource.Success<List<CharactersList>>(characters))
        }catch (e:HttpException){
            emit(Resource.Error<List<CharactersList>>(e.localizedMessage?:"An unexpected error occurred"))
        }
        catch (e:IOException){
            emit(Resource.Error<List<CharactersList>>("Could not reach the server.Check your internet connection"))
        }
    }
}