package id.kotlin.training.movies.services

interface NetworkCallback<in T> {

    fun onSuccess(response: T)

    fun onError(e: Throwable)
}