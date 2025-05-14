package com.example.animalsapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.animalsapp.R
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
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(30.dp),
                textAlign = TextAlign.Start
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
                    .padding(innerPadding).padding(20.dp)
            ) {
                AsyncImage(
                    model = animal.image,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    error = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                )
                Text(
                    text = animal.description,
                    color = Color.White,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Hechos" +
                            "\nInteresantes"
                    ,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF4F48D),
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                animal.facts.forEach { fact ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF3A3A3A))
                            .clickable {
                                // Aquí puedes agregar la acción al hacer clic en el hecho
                            }
                            .padding(12.dp)
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = Color(0xFFF4F48D)
                            )
                            Text(
                                text = fact,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                        }
                    }

                }
                Text(
                    text = "Galería",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF4F48D),
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                    LazyRow {
                        item {
                            animal.imageGallery.forEach { image ->
                            AsyncImage(
                                model = image,
                                contentDescription = null,
                                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                error = painterResource(R.drawable.ic_launcher_foreground),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .width(300.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }
                    }
                }
            }
            // Aquí puedes agregar más detalles del animal, como su imagen, galería, etc.
        }
    }
}