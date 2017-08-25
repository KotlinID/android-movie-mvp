package id.kotlin.training.movies.ext

import android.support.annotation.StringDef
import kotlin.annotation.AnnotationRetention.SOURCE

class Configs(@Key val key: String) {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val BASE_MOVIE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2"
        const val API_KEY = "37dc2d42ed324228aa382f0554ad1b28"
        const val DEFAULT_SORT = "popularity.desc"
    }

    @Retention(SOURCE)
    @StringDef(
            BASE_URL,
            BASE_MOVIE_URL,
            API_KEY,
            DEFAULT_SORT
    )
    annotation class Key
}