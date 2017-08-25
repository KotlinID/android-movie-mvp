package id.kotlin.training.movies.deps.component

import dagger.Component
import id.kotlin.training.movies.deps.module.NetworkModule
import id.kotlin.training.movies.deps.module.ServiceModule
import id.kotlin.training.movies.view.detail.DetailActivity
import id.kotlin.training.movies.view.home.MovieActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        NetworkModule::class,
        ServiceModule::class
))
interface ApplicationComponent {

    fun inject(movieActivity: MovieActivity)

    fun inject(detailActivity: DetailActivity)
}