package com.baharudin.spacex.data.rocket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketResponseItem(
    val company: String,
    val cost_per_launch: Int,
    val country: String,
    val description: String,
    val first_flight: String,
    val flickr_images: List<String>,
    val id: String,
    val name: String,
    val stages: Int,
    val success_rate_pct: Int,
    val type: String,
    val wikipedia: String
) : Parcelable