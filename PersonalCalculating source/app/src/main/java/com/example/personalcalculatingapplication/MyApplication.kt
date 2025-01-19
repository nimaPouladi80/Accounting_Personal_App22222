package com.example.personalcalculatingapplication

import android.app.Application
import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MyApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }
    override fun onCreate() {
        super.onCreate()

        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<ReminderWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance().enqueue(periodicWorkRequest)
    }
}