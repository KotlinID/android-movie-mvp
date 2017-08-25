package id.kotlin.training.movies.view.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import id.kotlin.training.movies.R
import id.kotlin.training.movies.data.local.Movie
import id.kotlin.training.movies.deps.provider.ApplicationProvider
import id.kotlin.training.movies.ext.loadImage
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    private var presenter: DetailPresenter? = null
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        (application as ApplicationProvider).providesApplicationComponent()
                                            .inject(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        fun initView() {
            movie = intent.getParcelableExtra("MOVIE")
            supportActionBar?.title = movie?.title
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        initView()
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    override fun onResume() {
        presenter = DetailPresenter()
        onAttach()
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onAttach() {
        presenter?.onAttach(this)
        presenter?.setMovieDetail(movie)
    }

    override fun onDetach() {
        presenter?.onDetach()
    }

    override fun onDetailMovie(image: String?,
                               desc: String?,
                               date: String?,
                               vote: Double?) {
        loadImage(this, image ?: "", ivDetail)

        tvDetailDescValue.text = desc
        tvDetailDateValue.text = date
        tvDetailRatingValue.text = vote.toString()
    }
}