package com.baharudin.spacex.network

import com.baharudin.spacex.data.CapsuleResponse
import com.baharudin.spacex.data.DragonResponse
import com.baharudin.spacex.data.DragonResponseItem
import retrofit2.Response
import retrofit2.http.GET


interface SpaceApi {

    @GET("capsules")
    suspend fun getAllCapsule() : Response<CapsuleResponse>
    @GET("dragons")
    suspend fun getAllDragon() : Response<DragonResponse>
}