package com.example.animalsapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AmbientsScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(innerPadding)
    ){
        Text(
            text = "Animals", color = Color.White
        )   
    }
}