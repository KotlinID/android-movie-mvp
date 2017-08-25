package id.kotlin.training.movies.view.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import id.kotlin.training.movies.R.layout
import id.kotlin.training.movies.deps.provider.ApplicationProvider
import id.kotlin.training.movies.services.DiscoverMovieService
import javax.inject.Inject

open class MovieActivity : AppCompatActivity(), MovieView {

    @Inject protected lateinit var service: DiscoverMovieService

    private var presenter: MoviePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_movie)
        (application as ApplicationProvider).providesApplicationComponent().inject(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    override fun onResume() {
        presenter = MoviePresenter()
        onAttach()
        super.onResume()
    }

    override fun onAttach() {
        presenter?.onAttach(this)
        presenter?.discoverMovie(service)
    }

    override fun onDetach() {
        presenter?.onDetach()
    }
}