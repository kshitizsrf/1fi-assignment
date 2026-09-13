package com.marketplace.one1fi.core.util

/**
 * Generic wrapper the whole data layer returns instead of raw models, so
 * every ViewModel handles loading/success/error the same way regardless
 * of which repository it talks to.
 */
sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : Resource<Nothing>()
}
