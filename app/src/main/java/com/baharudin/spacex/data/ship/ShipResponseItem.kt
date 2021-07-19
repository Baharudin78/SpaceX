package com.baharudin.spacex.data.ship

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShipResponseItem(
    val home_port: String,
    val id: String,
    val image: String,
    val name: String,
    val type: String,
    val year_built: Int
) : Parcelable