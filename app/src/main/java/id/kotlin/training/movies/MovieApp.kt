package id.kotlin.training.movies

import android.support.multidex.MultiDexApplication
import id.kotlin.training.movies.deps.component.ApplicationComponent
import id.kotlin.training.movies.deps.component.DaggerApplicationComponent
import id.kotlin.training.movies.deps.module.NetworkModule
import id.kotlin.training.movies.deps.module.ServiceModule
import id.kotlin.training.movies.deps.provider.ApplicationProvider

class MovieApp : MultiDexApplication(), ApplicationProvider {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                                              .networkModule(NetworkModule(this))
                                              .serviceModule(ServiceModule())
                                              .build()
    }

    override fun providesApplicationComponent(): ApplicationComponent = component
}