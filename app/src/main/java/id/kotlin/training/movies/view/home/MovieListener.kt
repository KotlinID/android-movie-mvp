package id.kotlin.training.movies.view.home

import id.kotlin.training.movies.data.local.Movie

interface MovieListener {

    fun onClick(movie: Movie)
}