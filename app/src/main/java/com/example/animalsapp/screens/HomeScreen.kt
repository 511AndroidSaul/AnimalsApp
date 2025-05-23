package com.example.animalsapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compost
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animalsapp.R
import com.example.animalsapp.models.Animals
import com.example.animalsapp.models.mockAnimals
import com.example.animalsapp.ui.theme.AnimalsAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animalsapp.services.AnimalsService
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun HomeScreen(innerPadding: PaddingValues, navController: NavController) {
    var animals by remember {
        mutableStateOf<List<Animals>>(emptyList())
    }
    val scope = rememberCoroutineScope()
    val BASE_URL = "https://animals.juanfrausto.com/api/"

    LaunchedEffect(key1 = true) {
        scope.launch {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val animalsService = retrofit.create(AnimalsService::class.java)
                animals = animalsService.getAnimals()

            } catch (e: Exception) {
                Log.e("HomeScreen", "Error al obtener animales", e)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = "Animales",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(30.dp),
                textAlign = TextAlign.Start
            )
            Box(
                modifier = Modifier
                    .padding(30.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF4F48D))
                    .width(100.dp)
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ){
                Row(){
                    Icon(
                        imageVector = Icons.Default.Compost,
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Agregar",
                        fontSize = 16.sp,
                        color = Color.Black,

                        )
                }

            }
        }
        Text(
            text = "Conoce a los animales más increíbles del mundo",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier
                .padding((25.dp))
                .padding(bottom = 22.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        animals.forEach { animal ->
            AsyncImage(
                model = animal.image,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(200.dp)
                    .clip(CircleShape)
                    .clickable{
                        navController.navigate("animal/${animal.id}")
                    }
            )
            Text(
                text = animal.name.split(" ").first(),
                fontSize = 24.sp,
                fontWeight =  FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                textAlign = TextAlign.Center)
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    AnimalsAppTheme {
        HomeScreen(innerPadding = PaddingValues(10.dp), navController = rememberNavController())
    }
}