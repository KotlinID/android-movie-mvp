package id.kotlin.training.movies.deps.module

import dagger.Module
import dagger.Provides
import id.kotlin.training.movies.data.Api
import id.kotlin.training.movies.services.DiscoverMovieService
import javax.inject.Singleton

@Module
open class ServiceModule {

    @Provides
    @Singleton
    protected fun providesDiscoverMovieService(api: Api) = DiscoverMovieService(api)
}