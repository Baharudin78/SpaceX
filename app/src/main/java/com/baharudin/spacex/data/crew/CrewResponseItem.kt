package com.baharudin.spacex.data.crew

data class CrewResponseItem(
    val agency: String,
    val id: String,
    val image: String,
    val launches: List<Any>,
    val name: String,
    val status: String,
    val wikipedia: String
)