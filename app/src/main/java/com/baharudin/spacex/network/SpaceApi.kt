package com.baharudin.spacex.network

import com.baharudin.spacex.data.CapsuleResponse
import retrofit2.http.GET


interface SpaceApi {

    @GET("capsules")
    fun getAllCapsule() : ArrayList<CapsuleResponse>
}