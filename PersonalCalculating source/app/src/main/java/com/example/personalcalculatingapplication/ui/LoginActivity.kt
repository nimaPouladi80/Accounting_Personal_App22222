package com.example.personalcalculatingapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.personalcalculatingapplication.PreferenceHelper
import com.example.personalcalculatingapplication.R
import kotlinx.android.synthetic.main.layout_login.*

class LoginActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)
        val preferenceHelper = PreferenceHelper(this)

        btLogin.setOnClickListener {
            val userName = et_name.text.toString().trim()

            if (userName.isNotBlank()){
                val name  = et_name.text.toString().trim()

                if (name.isEmpty()){
                    et_name.error ="لطفا مقدار نام را وارد کنید"
                    return@setOnClickListener
                }

                preferenceHelper.setUserName(name)
                startActivity(Intent(this , MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }
        }
    }
}