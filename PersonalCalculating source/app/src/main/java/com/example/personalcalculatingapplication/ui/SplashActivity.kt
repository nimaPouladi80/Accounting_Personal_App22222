package com.example.personalcalculatingapplication.ui

import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.personalcalculatingapplication.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_splash)


//        first date is : 1399-10-5  => diff(x , firstDate) >= 0
//        second date is : 1399-10-18 => diff(secondDate , x) >=0
//        getBaseDiff("1399-10-18 00:00:00", "1399-10-18 00:00:00")



        val handler = Handler()
        handler.postDelayed({
            val preferenceHelper = PreferenceHelper(this@SplashActivity)
            if (preferenceHelper.getUserName().isNotEmpty()) {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
            } else {
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
            }
        }, 2000)
    }
}