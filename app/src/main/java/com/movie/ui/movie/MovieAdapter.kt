package com.movie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.movie.data.model.MovieModel
import com.movie.databinding.ItemMovieBinding
import com.movie.util.limitDescription

class MovieAdapter(private val clickMovieDetails: ClickMovieDetails) :
    ListAdapter<MovieModel, MovieAdapter.ViewHolder>(MovieAdapterDiffCallback()) {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item, clickMovieDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(
            movieModel: MovieModel,
            clickMovieDetails: ClickMovieDetails
        ) {
            itemMovieBinding.moviemodel = movieModel
            itemMovieBinding.clickmoviedetails = clickMovieDetails
            itemMovieBinding.executePendingBindings()
            itemMovieBinding.tvDescriptionCharacter.text = movieModel.overview?.limitDescription(150)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemMovieBinding =
                    ItemMovieBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(itemMovieBinding)
            }
        }
    }
}

class MovieAdapterDiffCallback : DiffUtil.ItemCallback<MovieModel>() {

    override fun areItemsTheSame(
        oldItem: MovieModel,
        newItem: MovieModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieModel,
        newItem: MovieModel
    ): Boolean {
        return oldItem == newItem
    }
}

class ClickMovieDetails(val clickMovieDetails: (movieModel: MovieModel?) -> Unit) {
    fun onClick(movieModel: MovieModel?) = clickMovieDetails(movieModel)
}
