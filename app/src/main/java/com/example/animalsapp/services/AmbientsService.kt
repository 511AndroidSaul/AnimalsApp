package com.example.animalsapp.services

import com.example.animalsapp.models.Ambients
import retrofit2.http.GET

interface AmbientsService {
    @GET("environments")
    suspend fun getAmbients(): List<Ambients>

    @GET("environments/{id}")
    suspend fun getAmbientById(@retrofit2.http.Path("id") id: String): Ambients

}
