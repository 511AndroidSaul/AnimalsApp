package com.example.animalsapp.screens


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.animalsapp.models.Ambients
import retrofit2.Retrofit
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.animalsapp.R
import com.example.animalsapp.models.Animals
import com.example.animalsapp.services.AmbientsService
import com.example.animalsapp.services.AnimalsService
import kotlinx.coroutines.launch
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.foundation.lazy.items


@Composable
fun AmbientsDetailScreen(
    ambientId: String,
    innerPadding: PaddingValues = PaddingValues(),
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val BASE_URL = "https://animals.juanfrausto.com/api/"

    var ambient by remember {
        mutableStateOf<Ambients>(
            Ambients(
                id = "",
                name = "",
                description = "",
                image = ""
            )
        )
    }

    var isLoading by remember { mutableStateOf(true) }
    var animals by remember { mutableStateOf<List<Animals>>(emptyList()) }

    LaunchedEffect(key1 = true) {
        scope.launch {
            try {

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(AmbientsService::class.java)
                val animalsService = retrofit.create(AnimalsService::class.java)
                ambient = service.getAmbientById(ambientId)
                val allAnimals = animalsService.getAnimals()
                animals = allAnimals.filter { it.environmentId == ambientId }




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
            AsyncImage(
                model = ambient.image,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .height(300.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
            Text(
                text = ambient.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(30.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = ambient.description,
                color = Color.White,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Animales en este ambiente",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF4F48D),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            LazyRow {
                items(animals) { animal ->
                    AsyncImage(
                        model = animal.image,
                        contentDescription = animal.name,
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        error = painterResource(R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(150.dp)
                            .height(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
        }
    }
}