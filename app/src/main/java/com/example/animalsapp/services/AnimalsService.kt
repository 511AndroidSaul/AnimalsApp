package com.example.animalsapp.services

import com.example.animalsapp.models.Animals
import retrofit2.http.GET

interface AnimalsService {

    @GET("animals")
    suspend fun getAnimals(): List<Animals>

    @GET("animals/{id}")
    suspend fun getAnimalById(@retrofit2.http.Path("id") id: String): Animals

}