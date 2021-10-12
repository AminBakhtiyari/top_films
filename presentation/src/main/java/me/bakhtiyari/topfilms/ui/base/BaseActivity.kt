package me.bakhtiyari.topfilms.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(
    private val layout: Int
) : AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this


        initVariables()
        initObserves()
        initViews()

    }

    abstract fun initVariables()
    abstract fun initObserves()
    abstract fun initViews()
}