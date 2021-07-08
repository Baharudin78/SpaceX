package com.baharudin.spacex.network

class SpaceRepository
    (val spaceApi: SpaceApi)
{
    suspend fun getAllCapsule() =
        spaceApi.getAllCapsule()

    suspend fun getAllDragon() =
        spaceApi.getAllDragon()

}