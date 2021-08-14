package com.baharudin.spacex.data.launcher

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Flickr(
    val original: List<String>,
    val small: List<String>
) : Parcelable