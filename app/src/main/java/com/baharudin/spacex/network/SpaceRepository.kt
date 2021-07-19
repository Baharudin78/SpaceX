package com.baharudin.spacex.network

class SpaceRepository{
    suspend fun getAllDragon() =
        RetrofitInstance.api.getAllDragon()
    suspend fun getAllShip() =
        RetrofitInstance.api.getAllShip()
    suspend fun getAllRocket() =
        RetrofitInstance.api.getAllRocket()
    suspend fun getAllCrew() =
        RetrofitInstance.api.getAllCrew()
}