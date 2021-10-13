package me.bakhtiyari.topfilms.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.bakhtiyari.topfilms.R
import me.bakhtiyari.topfilms.databinding.ItemGridNormallMovieContentsBinding
import me.bakhtiyari.topfilms.domain.model.MovieModel

class MovieListAdapter(private val listener: HandlerActionsListener) :
    PagingDataAdapter<MovieModel, MovieListAdapter.ViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemGridNormallMovieContentsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_grid_normall_movie_contents,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemGridNormallMovieContentsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieModel) {
            binding.setVariable(BR.actionsListener, listener)
            binding.setVariable(BR.content, item)
            binding.executePendingBindings()
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }
}