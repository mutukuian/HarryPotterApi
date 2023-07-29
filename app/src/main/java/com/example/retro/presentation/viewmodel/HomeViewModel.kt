package com.example.retro.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retro.core.common.Resource
import com.example.retro.domain.model.CharactersList
import com.example.retro.domain.use_case.get_characters_List.GetCharactersListUseCase
import com.example.retro.presentation.states.CharactersListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) :ViewModel(){
    private val _state = mutableStateOf(CharactersListState())
    val state: State<CharactersListState> = _state

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getCharactersListUseCase().onEach {
            result ->
            when(result){
                is Resource.Success ->{
                    _state.value = CharactersListState(characters = result.data?: emptyList())
                }
                is Resource.Error ->{
                    _state.value = CharactersListState(error = result.message?:"An unexpected error occurred")
                }
                is Resource.Loading ->{
                    _state.value = CharactersListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}