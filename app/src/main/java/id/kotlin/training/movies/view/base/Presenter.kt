package id.kotlin.training.movies.view.base

interface Presenter<in T : View> {

    fun onAttach(view: T)

    fun onDetach()
}