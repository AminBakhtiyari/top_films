package me.bakhtiyari.topfilms.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.bakhtiyari.topfilms.R
import me.bakhtiyari.topfilms.ui.main.MainActivity
import me.bakhtiyari.topfilms.util.delay
import me.bakhtiyari.topfilms.util.runActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delay(3000) {
            this.runActivity(c = MainActivity::class.java, finish = true)
        }
    }
}