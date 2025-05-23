package com.example.animalsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animalsapp.screens.AmbientsDetailScreen
import com.example.animalsapp.screens.AmbientsScreen
import com.example.animalsapp.screens.AnimalDetailScreen
import com.example.animalsapp.screens.HomeScreen
import com.example.animalsapp.ui.theme.AnimalsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimalsAppTheme {
                var selectedScreen by remember { mutableStateOf("inicio") }
                val navController = rememberNavController() // Inicialización del navController

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF2E372E)), // Color de fondo
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    bottomBar = {
                        NavigationBar(containerColor = Color(0xFFF4F48D),
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 26.dp) // Espaciado general
                                .navigationBarsPadding() // Ajuste automático al sistema (opcional)
                                .clip(RoundedCornerShape(50.dp))
                                .height(60.dp)
                        ){
                            NavigationBarItem(
                                selected = selectedScreen == "inicio",
                                onClick = { selectedScreen = "inicio"
                                          navController.navigate("inicio") },
                                icon = {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.Pets,
                                            contentDescription = "Inicio",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Text(
                                            modifier = Modifier.padding(start = 5.dp),
                                            text = "Inicio",
                                            color = Color.Black
                                        )
                                    }
                                }
                            )
                            NavigationBarItem(
                                selected = selectedScreen == "ambiente",
                                onClick = { selectedScreen = "ambiente"
                                          navController.navigate("ambients") },
                                icon = {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.Forest,
                                            contentDescription = "Ambiente",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Text(
                                            modifier = Modifier.padding(start = 5.dp),
                                            text = "Ambiente",
                                            color = Color.Black
                                        )
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController, startDestination = "inicio"
                    ) {
                        composable(route = "inicio") {
                            HomeScreen( innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = "ambients"){
                            AmbientsScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = "animal/{id}") { backStackEntry ->
                            val animalId = backStackEntry.arguments?.getString("id") // Corregido
                            animalId?.let {
                                AnimalDetailScreen(animalId = it, innerPadding = innerPadding, navController = navController)
                            }
                        }
                        composable(route = "environment/{id}") { backStackEntry ->
                            val ambientId = backStackEntry.arguments?.getString("id") // Corregido
                            ambientId?.let {
                                AmbientsDetailScreen(ambientId = it, innerPadding = innerPadding, navController = navController)
                            }
                        }

                    }
                }
            }
        }
    }
}