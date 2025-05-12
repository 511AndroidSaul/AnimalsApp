package com.example.animalsapp.models

data class Animals(
    val description: String,
    val environmentId: String,
    val facts: List<String>,
    val id: String,
    val image: String,
    val imageGallery: List<String>,
    val name: String
)

val mockAnimals = Animals(
    id = "680d2122d10bbc79144edbbe",
    name = "Nutria Asian Small-Clawed",
    description = "La nutria asian small clawed otter (Amblonyx cinereus) es una especie de la familia Mustelidae y es la más pequeña de todas las especies de nutrias. Se distingue por sus garras cortas y su capacidad para utilizarlas hábilmente.",
    image = "https://i.redd.it/04rwloagnx551.jpg",
    imageGallery = listOf("https://i.ytimg.com/vi/VcF8U1FAjoc/maxresdefault.jpg",
    "https://i.ytimg.com/vi/BgkVsTNTebM/sddefault.jpg"),
    environmentId = "680c0510d10bbc79144edbb8",
    facts = listOf("Las nutrias asian small clawed otter son conocidas por su comportamiento social y a menudo forman grupos familiares.",
        "Utilizan sus garras cortas para capturar presas y manipular objetos en su entorno acuático.",
        "Son muy ágiles en el agua y pueden nadar rápidamente para cazar peces y otros animales acuáticos.")

)
