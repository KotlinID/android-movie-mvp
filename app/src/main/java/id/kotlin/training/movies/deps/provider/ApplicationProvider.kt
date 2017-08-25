package id.kotlin.training.movies.deps.provider

import id.kotlin.training.movies.deps.component.ApplicationComponent

interface ApplicationProvider {

    fun providesApplicationComponent(): ApplicationComponent
}