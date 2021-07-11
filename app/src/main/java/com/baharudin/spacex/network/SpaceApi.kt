package com.baharudin.spacex.network

import com.baharudin.spacex.data.DragonResponse
import com.baharudin.spacex.data.RocketResponse
import com.baharudin.spacex.data.ShipResponse
import retrofit2.Response
import retrofit2.http.GET


interface SpaceApi {
    @GET("dragons")
    suspend fun getAllDragon() : Response<DragonResponse>
    @GET("ships")
    suspend fun getAllShip() : Response<ShipResponse>
    @GET("rockets")
    suspend fun getAllRocket() : Response<RocketResponse>
}