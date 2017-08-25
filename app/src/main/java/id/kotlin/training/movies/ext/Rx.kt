package id.kotlin.training.movies.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber

internal fun CompositeDisposable?.safeDispose() {
    this?.let { this.clear() }
}

internal fun <T : Any> disposableSubscriber(next: (T) -> Unit = {},
                                            error: (Throwable) -> Unit = {},
                                            complete: () -> Unit = {}): DisposableSubscriber<T> {
    return object : DisposableSubscriber<T>() {
        override fun onNext(response: T) {
            next(response)
        }

        override fun onError(e: Throwable) {
            error(e)
        }

        override fun onComplete() {
            complete()
        }
    }
}