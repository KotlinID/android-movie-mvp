package id.kotlin.training.movies.services

import id.kotlin.training.movies.data.Api
import id.kotlin.training.movies.data.remote.DiscoverMovie
import id.kotlin.training.movies.ext.disposableSubscriber
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

class DiscoverMovieService(private val api: Api) {

    fun discoverMovie(key: String,
                      sortBy: String,
                      callback: NetworkCallback<DiscoverMovie>): Disposable {
        return api.discoverMovie(key, sortBy)
                  .compose(NetworkCallTransformer<DiscoverMovie>())
                  .onErrorResumeNext(Function { Flowable.error { it } })
                  .subscribeWith(disposableSubscriber<DiscoverMovie>(
                          next = { response -> callback.onSuccess(response) },
                          error = { exception -> callback.onError(exception) }
                  ))
    }
}