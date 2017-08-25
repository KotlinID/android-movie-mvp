package id.kotlin.training.movies.view.home

import android.content.Context
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kotlin.training.movies.R
import id.kotlin.training.movies.data.local.Movie
import id.kotlin.training.movies.ext.loadImage
import id.kotlin.training.movies.view.home.MovieAdapter.MovieHolder
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
        private val context: Context,
        private val movies: List<Movie>,
        private val listener: MovieListener
) : Adapter<MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieHolder = MovieHolder(
            LayoutInflater.from(parent?.context).inflate(
                    R.layout.item_movie,
                    parent,
                    false
            )
    )

    override fun onBindViewHolder(holder: MovieHolder?, position: Int) {
        val movie = movies[holder?.adapterPosition ?: 0]
        holder?.bindView(context, movie, listener)
    }

    override fun getItemCount(): Int = movies.size

    class MovieHolder(itemView: View) : ViewHolder(itemView) {

        fun bindView(context: Context,
                     movie: Movie,
                     listener: MovieListener) {
            with(movie) {
                loadImage(context, movie.image, itemView.ivMovie)
                itemView.ivMovie.setOnClickListener { listener.onClick(movie) }
            }
        }
    }
}