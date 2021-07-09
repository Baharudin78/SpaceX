package com.baharudin.spacex.network

class SpaceRepository{
    suspend fun getAllDragon() =
        RetrofitInstance.api.getAllDragon()
    suspend fun getAllShip() =
        RetrofitInstance.api.getAllShip()
}