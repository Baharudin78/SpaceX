package com.baharudin.spacex.network

class SpaceRepository(
    val spaceApi: SpaceApi
    ) {

    fun getAllCapsule() {
        spaceApi.getAllCapsule()
    }
}