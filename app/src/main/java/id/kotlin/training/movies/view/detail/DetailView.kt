package id.kotlin.training.movies.view.detail

import id.kotlin.training.movies.view.base.View

interface DetailView : View {

    fun onDetailMovie(image: String?,
                      desc: String?,
                      date: String?,
                      vote: Double?)
}