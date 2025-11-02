package com.muss_coding.solidweather.domain.util

/*
 * Domain Layer: Utility Class
 * This is a generic wrapper class we use to encapsulate data that
 * comes from the repository. It can be in one of three states:
 * Success (with data), Error (with a message), or Loading.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}
