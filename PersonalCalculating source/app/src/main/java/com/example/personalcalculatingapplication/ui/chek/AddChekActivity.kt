package com.example.personalcalculatingapplication.ui.chek

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.sql.CalculatingDatabaseHelper
import com.example.personalcalculatingapplication.sql.Chek
import com.example.personalcalculatingapplication.sql.G
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import kotlinx.android.synthetic.main.layout_add_chek.*

class AddChekActivity : AppCompatActivity() {

    var selectedStatus ="برگشتی"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_add_chek)

        val subject = intent.getStringExtra("SUBJECT")

        tv_title.text = "اضافه کردن $subject"

        bt_back.setOnClickListener { onBackPressed() }

        et_created_date.setOnClickListener {
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

                    et_created_date.text = "$year/$selectedMonth/$selectedDay"

                },
                persianCalendar.persianYear,
                persianCalendar.persianMonth,
                persianCalendar.persianDay
            )
            datePickerDialog.show(
                (this@AddChekActivity as Activity).fragmentManager,
                getString(R.string.app_name)
            )
        }

        et_end_date.setOnClickListener {
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

                    et_end_date.text = "$year/$selectedMonth/$selectedDay"

                },
                persianCalendar.persianYear,
                persianCalendar.persianMonth,
                persianCalendar.persianDay
            )
            datePickerDialog.show(
                (this@AddChekActivity as Activity).fragmentManager,
                getString(R.string.app_name)
            )
        }

        bargashti.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedStatus = bargashti.text.toString()
            }
        }
        passed.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedStatus = passed.text.toString()
            }
        }
        moalaq.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedStatus = moalaq.text.toString()
            }
        }

        btSave.setOnClickListener {
            val cost = et_cost.text.toString().trim()
            val createdDate = et_created_date.text.toString().trim()
            val endDate = et_end_date.text.toString().trim()
            val serial = et_serial.text.toString().trim()
            val desc = et_desc.text.toString().trim()

            if (cost.isEmpty()) {
                et_cost.error = "لطفا مقدار مبلغ را وارد کنید"
                return@setOnClickListener
            }
            if (createdDate.isEmpty()) {
                et_created_date.error = "لطفا مقدار تاریخ صدور را وارد کنید"
                return@setOnClickListener
            }
            if (endDate.isEmpty()) {
                et_end_date.error = "لطفا مقدار تاریخ سررسید را وارد کنید"
                return@setOnClickListener
            }
            if (serial.isEmpty()) {
                et_serial.error = "لطفا مقدار شماره سریال را وارد کنید"
                return@setOnClickListener
            }

            val item = CalculatingDatabaseHelper(this, G.DBNAME, 1)
            val hasNoitfy = if (check_notif.isChecked)
                "1"
            else
                "0"
            item.addChek(Chek(subject.toString(), cost, desc , hasNoitfy , serial , selectedStatus , createdDate , endDate))

            Toast.makeText(this, getString(R.string.add_item_success), Toast.LENGTH_SHORT).show()


            onBackPressed()
        }
    }
}