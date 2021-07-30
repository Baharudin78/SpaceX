package com.baharudin.spacex.data.dragon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DragonResponseItem(
    val active: Boolean,
    val crew_capacity: Int,
    val description: String,
    val dry_mass_kg: Int,
    val dry_mass_lb: Int,
    val first_flight: String,
    val flickr_images: List<String>,
    val id: String,
    val name: String,
    val wikipedia: String,
    val type : String
) : Parcelable