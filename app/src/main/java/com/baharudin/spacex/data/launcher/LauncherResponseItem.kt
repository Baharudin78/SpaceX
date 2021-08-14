package com.baharudin.spacex.data.launcher

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LauncherResponseItem(
    val auto_update: Boolean,
    val cores: List<String>,
    val crew: List<String>,
    val date_local: String,
    val date_precision: String,
    val date_unix: Int,
    val date_utc: String,
    val details: String,
    val flight_number: Int,
    val id: String,
    val launch_library_id: String,
    val launchpad: String,
    val name: String,
    val net: Boolean,
    val payloads: List<String>,
    val rocket: String,
    val ships: List<String>,
    val static_fire_date_unix: Int,
    val static_fire_date_utc: String,
    val success: Boolean,
    val tbd: Boolean,
    val upcoming: Boolean,
    val window: Int
) : Parcelable