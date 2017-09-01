package id.kotlin.training.movies.view.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import id.kotlin.training.movies.R
import id.kotlin.training.movies.data.local.Movie
import id.kotlin.training.movies.data.remote.DiscoverMovie
import id.kotlin.training.movies.deps.provider.ApplicationProvider
import id.kotlin.training.movies.ext.Config
import id.kotlin.training.movies.ext.Configs
import id.kotlin.training.movies.ext.ItemDecoration
import id.kotlin.training.movies.ext.getDate
import id.kotlin.training.movies.services.DiscoverMovieService
import id.kotlin.training.movies.view.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_movie.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

open class MovieActivity : AppCompatActivity(), MovieView {

    @Inject protected lateinit var service: DiscoverMovieService

    private var presenter: MoviePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        (application as ApplicationProvider).providesApplicationComponent()
                                            .inject(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        fun initView() {
            supportActionBar?.title = title

            val layoutManager = GridLayoutManager(
                    this,
                    3,
                    LinearLayoutManager.VERTICAL,
                    false
            )
            rvMovie.layoutManager = layoutManager
            rvMovie.addItemDecoration(ItemDecoration(
                    30,
                    30,
                    true
            ))
        }

        initView()
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

    override fun onProgress() {
        pbMovie.visibility = View.VISIBLE
    }

    override fun onSuccess(response: DiscoverMovie) {
        @Configs val movieBaseUrl = Config.BASE_MOVIE_URL

        val data = response.results
        val movies = data.map {
            val title = it.title
            val desc = it.overview
            val date = getDate(it.releaseDate)
            val images = movieBaseUrl.plus(it.posterPath)
            val vote = it.voteAverage

            Movie(title, desc, date, images, vote)
        }

        val adapter = MovieAdapter(this, movies, object : MovieListener {
            override fun onClick(movie: Movie) {
                presenter?.openMovieDetail(movie)
            }
        })
        rvMovie.adapter = adapter
        adapter.notifyDataSetChanged()

        fun hide() {
            pbMovie.visibility = View.GONE
            rvMovie.visibility = View.VISIBLE
        }

        hide()
    }

    override fun onOpenMovieDetail(movie: Movie) {
        startActivity<DetailActivity>(
                "MOVIE" to movie
        )
    }
}