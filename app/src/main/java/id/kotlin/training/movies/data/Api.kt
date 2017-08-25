package id.kotlin.training.movies.data

import id.kotlin.training.movies.data.remote.DiscoverMovie
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("3/discover/movie")
    fun discoverMovie(@Query("api_key") key: String,
                      @Query("sort_by") sortBy: String): Flowable<DiscoverMovie>
}