package com.baharudin.spacex.network

import com.baharudin.spacex.data.crew.CrewResponse
import com.baharudin.spacex.data.dragon.DragonResponse
import com.baharudin.spacex.data.rocket.RocketResponse
import com.baharudin.spacex.data.ship.ShipResponse
import retrofit2.Response
import retrofit2.http.GET


interface SpaceApi {
    @GET("dragons")
    suspend fun getAllDragon() : Response<DragonResponse>
    @GET("ships")
    suspend fun getAllShip() : Response<ShipResponse>
    @GET("rockets")
    suspend fun getAllRocket() : Response<RocketResponse>
    @GET("crew")
    suspend fun getAllCrew() : Response<CrewResponse>
}