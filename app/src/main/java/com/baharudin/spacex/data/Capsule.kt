package com.baharudin.spacex.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Capsule (
    val id: String,
    val land_landings: Int,
    val last_update: String,
    val launches: List<String>,
    val reuse_count: Int,
    val serial: String,
    val status: String,
    val type: String,
    val water_landings: Int
        ) : Parcelable