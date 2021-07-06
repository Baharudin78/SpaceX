package com.baharudin.spacex.util

sealed class Resource<T>(
    val data : T? = null,
    val messege : String? = null
) {
    class Succes<T>(data: T?) : Resource<T>(data)
    class Error<T>(messege: String?,data: T?) : Resource<T>(data, messege)
    class Loading<T>() : Resource<T>()

}
