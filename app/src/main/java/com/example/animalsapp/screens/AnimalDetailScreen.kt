package com.example.animalsapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.animalsapp.models.Animals
import retrofit2.Retrofit
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.animalsapp.services.AnimalsService
import kotlinx.coroutines.launch
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun AnimalDetailScreen(
    animalId: String,
    innerPadding: PaddingValues = PaddingValues(),
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val BASE_URL = "https://animals.juanfrausto.com/api/"

    var animal by remember {
        mutableStateOf<Animals>(
            Animals(
                id = "",
                name = "",
                description = "",
                image = "",
                imageGallery = emptyList(),
                environmentId = "",
                facts = emptyList()
            )
        )
    }

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        scope.launch {
            try {
                Log.d("AnimalDetailScreen", "Recibiendo animalId: $animalId")

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(AnimalsService::class.java)
                animal = service.getAnimalById(animalId)
                isLoading = false
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
                isLoading = false
            }
        }
    }
    if (isLoading) {
        Text(
            text = "Cargando...",
            color = Color.White,
            modifier = Modifier
                .padding(innerPadding)
        )
    } else {
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text(
                text = animal.name,
                color = Color.White,

            )
            Text(
                text = animal.description,
                color = Color.White,
            )
            // Aquí puedes agregar más detalles del animal, como su imagen, galería, etc.
        }
    }
}