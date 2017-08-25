package id.kotlin.training.movies.view.detail

import id.kotlin.training.movies.data.local.Movie
import id.kotlin.training.movies.ext.Configs
import id.kotlin.training.movies.view.base.Presenter

class DetailPresenter : Presenter<DetailView> {

    private var view: DetailView? = null

    override fun onAttach(view: DetailView) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    fun setMovieDetail(movie: Movie?) {
        @Configs val image = movie?.image

        val desc = movie?.desc
        val date = movie?.date
        val vote = movie?.vote

        view?.onDetailMovie(image, desc, date, vote)
    }
}