package com.example.personalcalculatingapplication.ui.income

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.sql.CalculatingDatabaseHelper
import com.example.personalcalculatingapplication.sql.Income
import com.example.personalcalculatingapplication.sql.G
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import kotlinx.android.synthetic.main.layout_add.*
import kotlinx.android.synthetic.main.layout_add.bt_back

class AddIncomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_add)

        val subject = intent.getStringExtra("SUBJECT")

        tv_title.text = "اضافه کردن $subject"

        bt_back.setOnClickListener { onBackPressed() }

        et_date.setOnClickListener {
            val persianCalendar = PersianCalendar()
            @SuppressLint("SetTextI18n") val datePickerDialog = DatePickerDialog.newInstance(
                { view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int ->

                    var selectedMonth = ""
                    var selectedDay = ""
                    selectedMonth = if ((monthOfYear + 1).toString().length ==1)
                        "0${monthOfYear +1}"
                    else
                        (monthOfYear+1).toString()

                    selectedDay = if (dayOfMonth.toString().length ==1)
                        "0$dayOfMonth"
                    else
                        "$dayOfMonth"

                    et_date.text = "$year/$selectedMonth/$selectedDay"

                },
                persianCalendar.persianYear,
                persianCalendar.persianMonth,
                persianCalendar.persianDay
            )
            datePickerDialog.show(
                (this@AddIncomeActivity as Activity).fragmentManager,
                getString(R.string.app_name)
            )
        }

        btSave.setOnClickListener {
            val cost = et_cost.text.toString().trim()
            val date = et_date.text.toString().trim()
            val desc = et_desc.text.toString().trim()

            if (cost.isEmpty()) {
                et_cost.error = "لطفا مقدار مبلغ را وارد کنید"
                return@setOnClickListener
            }
            if (date.isEmpty()) {
                et_date.error = "لطفا مقدار تاریخ را وارد کنید"
                return@setOnClickListener
            }
            if (desc.isEmpty()) {
                et_desc.error = "لطفا مقدار توضیحات را وارد کنید"
                return@setOnClickListener
            }

            val item = CalculatingDatabaseHelper(this, G.DBNAME, 1)

            val hasNoitfy = if (check_notif.isChecked)
                "1"
            else
                "0"

            item.addIncome(Income(subject.toString(), cost, date, desc , hasNoitfy))

            Toast.makeText(this, getString(R.string.add_item_success), Toast.LENGTH_SHORT).show()

            onBackPressed()
        }
    }
}