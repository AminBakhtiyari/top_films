package me.bakhtiyari.topfilms.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.bakhtiyari.topfilms.R

class MainActivity : AppCompatActivity() {

    /*val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment }
    val navController by lazy { navHostFragment.navController }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}