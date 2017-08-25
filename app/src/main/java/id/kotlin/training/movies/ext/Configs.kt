package id.kotlin.training.movies.ext

import android.support.annotation.StringDef
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@StringDef(
        Config.BASE_URL,
        Config.BASE_MOVIE_URL,
        Config.API_KEY,
        Config.DEFAULT_SORT
)
annotation class Configs

object Config {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val BASE_MOVIE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2"
    const val API_KEY = "37dc2d42ed324228aa382f0554ad1b28"
    const val DEFAULT_SORT = "popularity.desc"
}