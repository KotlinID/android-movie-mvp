package id.kotlin.training.movies.view.home

import android.util.Log
import id.kotlin.training.movies.data.local.Movie
import id.kotlin.training.movies.data.remote.DiscoverMovie
import id.kotlin.training.movies.ext.Config
import id.kotlin.training.movies.ext.Configs
import id.kotlin.training.movies.ext.safeDispose
import id.kotlin.training.movies.services.DiscoverMovieService
import id.kotlin.training.movies.services.NetworkCallback
import id.kotlin.training.movies.view.base.Presenter
import io.reactivex.disposables.CompositeDisposable

class MoviePresenter : Presenter<MovieView> {

    private var view: MovieView? = null
    private var disposables: CompositeDisposable? = null

    override fun onAttach(view: MovieView) {
        this.view = view
        disposables = CompositeDisposable()
    }

    override fun onDetach() {
        view = null
        disposables.safeDispose()
    }

    fun discoverMovie(service: DiscoverMovieService) {
        view?.onProgress()

        @Configs val apiKey = Config.API_KEY
        @Configs val defaultSort = Config.DEFAULT_SORT

        val disposable = service.discoverMovie(apiKey, defaultSort,
                object : NetworkCallback<DiscoverMovie> {
                    override fun onSuccess(response: DiscoverMovie) {
                        view?.onSuccess(response)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("MOVIE", e.message, e)
                    }
                })

        disposable.let { disposables?.add(it) }
    }

    fun openMovieDetail(movie: Movie) {
        view?.onOpenMovieDetail(movie)
    }
}