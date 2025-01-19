package com.example.personalcalculatingapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.personalcalculatingapplication.R
import kotlinx.android.synthetic.main.layout_about_us.*

class AboutUsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_about_us)

        bt_back.setOnClickListener { onBackPressed() }
    }
}