package com.example.retro.presentation.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.retro.domain.model.CharactersList
import com.example.retro.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel:HomeViewModel= hiltViewModel()
){
    val state = viewModel.state.value

    LazyColumn {
        if (state.isLoading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }

        items(state.characters) { character: CharactersList ->
            CharacterImageCard(character = character)
        }
    }

}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterImageCard(character:CharactersList){
    val imagePainter = rememberImagePainter(data = character.image)
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
     Box{

         Image(
             painter = imagePainter,
             contentDescription = null,
             modifier = Modifier
                 .fillMaxWidth()
                 .height(200.dp),
             contentScale = ContentScale.FillBounds
         )

         Surface(
             color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
             modifier = Modifier
                 .align(
                 Alignment.BottomCenter
             ),
             contentColor = MaterialTheme.colorScheme.surface
         ) {
             Column(modifier = Modifier
                 .fillMaxWidth()
                 .padding(4.dp)) {
                 Text(text = "Real Name: ${character.actor}")
                 Text(text = "Actor Name: ${character.name}")
                 Text(text = "Sex: ${character.gender}")
                 Text(text = "D.O.B: ${character.yearOfBirth}")

             }
         }

     }

    }
}