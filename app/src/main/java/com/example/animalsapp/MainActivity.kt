package com.example.animalsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animalsapp.ui.theme.AnimalsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimalsAppTheme {
                var selectedSreen by remember {
                mutableStateOf("inicio")
            }
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                        .background(Color(0xFF2E372E)),//color prueba
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color(0xFFF4F48D)
                        ){
                            NavigationBarItem(
                                selected = selectedSreen == "inicio",
                                onClick = {
                                    selectedSreen = "inicio"
                                },
                                icon = {
                                    Row(){
                                        Icon(
                                            imageVector = Icons.Default.Star, //icono prueba
                                            contentDescription = "inicio",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Text(
                                            modifier = Modifier.padding(start = 5.dp),
                                            text = "Inicio",
                                            color = Color.Black
                                        )

                                    }
                                },
                            )
                            NavigationBarItem(
                                selected = selectedSreen == "ambiente",
                                onClick = {
                                    selectedSreen = "ambiente"
                                },
                                icon = {
                                    Row(){
                                        Icon(
                                            imageVector = Icons.Default.List, //icono prueba
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
                                },
                            )
                        }
                    }
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimalsAppTheme {
        Greeting("Android")
    }
}