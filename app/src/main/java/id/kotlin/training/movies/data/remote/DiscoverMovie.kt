package id.kotlin.training.movies.data.remote

import com.google.gson.annotations.SerializedName

data class DiscoverMovie(@SerializedName("page") val page: Int,
                         @SerializedName("total_results") val totalResults: Long,
                         @SerializedName("total_pages") val totalPages: Long,
                         @SerializedName("results") val results: List<Result>) {

    data class Result(@SerializedName("id") val id: Int,
                      @SerializedName("vote_count") val voteCount: Int,
                      @SerializedName("video") val video: Boolean,
                      @SerializedName("vote_average") val voteAverage: Double,
                      @SerializedName("title") val title: String,
                      @SerializedName("popularity") val popularity: Double,
                      @SerializedName("poster_path") val posterPath: String,
                      @SerializedName("original_language") val originalLanguage: String,
                      @SerializedName("original_title") val originalTitle: String,
                      @SerializedName("genre_ids") val genreIds: List<Long>,
                      @SerializedName("backdrop_path") val backdropPath: String,
                      @SerializedName("adult") val adult: Boolean,
                      @SerializedName("overview") val overview: String,
                      @SerializedName("release_date") val releaseDate: String)
}