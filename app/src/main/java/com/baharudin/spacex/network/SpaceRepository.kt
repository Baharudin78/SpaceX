package com.baharudin.spacex.network

class SpaceRepository{
    suspend fun getAllCapsule() =
        RetrofitInstance.api.getAllCapsule()
    suspend fun getAllDragon() =
        RetrofitInstance.api.getAllDragon()
}