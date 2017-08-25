package id.kotlin.training.movies.view.home

import id.kotlin.training.movies.data.local.Movie
import id.kotlin.training.movies.data.remote.DiscoverMovie
import id.kotlin.training.movies.view.base.View

interface MovieView : View {

    fun onProgress()

    fun onSuccess(response: DiscoverMovie)

    fun onOpenMovieDetail(movie: Movie)
}