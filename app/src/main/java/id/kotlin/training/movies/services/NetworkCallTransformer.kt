package id.kotlin.training.movies.services

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class NetworkCallTransformer<T> : FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> =
    upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                         .unsubscribeOn(Schedulers.io())
                                         .cache()
}