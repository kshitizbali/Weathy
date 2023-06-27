package com.kshitizbali.weathy.domain.util

/**
 * A generic class that holds a value with its loading status and a failure status
 * @param <T>
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}
