package com.baharudin.spacex.data.crew

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CrewResponseItem(
    val agency: String,
    val id: String,
    val image: String,
    val name: String,
    val status: String,
    val wikipedia: String
) : Parcelable