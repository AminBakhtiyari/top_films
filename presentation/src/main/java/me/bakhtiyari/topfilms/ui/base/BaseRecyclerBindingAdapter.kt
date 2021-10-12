package me.bakhtiyari.topfilms.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerBindingAdapter<E>(

    val data: List<E>,
    val layout: Int,
    val fn: (ViewDataBinding, E, Int) -> Unit

) : RecyclerView.Adapter<BaseRecyclerBindingAdapter<E>.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            layout,
            parent,
            false
        )

        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(data[position])

    }

    inner class ViewHolder(val view: ViewDataBinding) : RecyclerView.ViewHolder(view.root) {

        fun bindView(data: E) {
            fn(view, data, adapterPosition)
        }

    }
}