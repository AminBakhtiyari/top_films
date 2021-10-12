package me.bakhtiyari.topfilms.ui.base

import androidx.databinding.ViewDataBinding

class UniversalDataBindingRecyclerAdapter<T>(
    data: List<T>,
    layout: Int,
    fn: (ViewDataBinding, T, Int) -> Unit
) : BaseRecyclerBindingAdapter<T>(data, layout, fn)