package com.example.personalcalculatingapplication.ui.payment

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.sql.CalculatingDatabaseHelper
import com.example.personalcalculatingapplication.sql.G
import com.example.personalcalculatingapplication.sql.Payment
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import kotlinx.android.synthetic.main.layout_add_payment.*

class AddPaymentActivity : AppCompatActivity() {

    var selectedLoan = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_add_payment)

        tv_title.text = "اضافه کردن قسط"

        bt_back.setOnClickListener { onBackPressed() }

        val db = CalculatingDatabaseHelper(this, G.DBNAME, 1)
        val list = db.getAllLoans()
        if (list.isNotEmpty()){
            val mlist = ArrayList<String>()
            mlist.add(0, "وام مرتبط را انتخاب کنید")
            for (i in list)
                mlist.add(i.desc)

            spinner_related_loan.adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                mlist 
            )

            spinner_related_loan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position > 0)
                        selectedLoan = mlist[position]
                }
                override fun onNothingSelected(parentView: AdapterView<*>) {}
            }

            et_date.setOnClickListener {
                val persianCalendar = PersianCalendar()
                @SuppressLint("SetTextI18n") val datePickerDialog = DatePickerDialog.newInstance(
                    { view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int ->

                        var selectedMonth = ""
                        var selectedDay = ""
                        selectedMonth = if ((monthOfYear + 1).toString().length == 1)
                            "0${monthOfYear + 1}"
                        else
                            (monthOfYear + 1).toString()

                        selectedDay = if (dayOfMonth.toString().length == 1)
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
                    (this@AddPaymentActivity as Activity).fragmentManager,
                    getString(R.string.app_name)
                )
            }

            btSave.setOnClickListener {
                val cost = et_cost.text.toString().trim()
                val date = et_date.text.toString().trim()
                val desc = et_desc.text.toString().trim()
                val count = et_count.text.toString().trim()
                val eachCost = et_each_cost.text.toString().trim()

                if (cost.isEmpty()) {
                    et_cost.error = "لطفا مقدار مبلغ را وارد کنید"
                    return@setOnClickListener
                }
                if (date.isEmpty()) {
                    et_date.error = "لطفا مقدار تاریخ را وارد کنید"
                    return@setOnClickListener
                }
                if (count.isEmpty()) {
                    et_desc.error = "لطفا مقدار تعداد اقساط را وارد کنید"
                    return@setOnClickListener
                }
                if (eachCost.isEmpty()) {
                    et_desc.error = "لطفا مقدار مبلغ هر قسط را وارد کنید"
                    return@setOnClickListener
                }
                if (selectedLoan.isEmpty()){
                    Toast.makeText(
                        this@AddPaymentActivity,
                        "لطفا وام مرتبط را انتخاب کنید",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val item = CalculatingDatabaseHelper(this, G.DBNAME, 1)

                val hasNoitfy = if (check_notif.isChecked)
                    "1"
                else
                    "0"

                item.addPayment(
                    Payment(
                        "قسط",
                        cost,
                        date,
                        desc,
                        hasNoitfy,
                        count,
                        eachCost,
                        selectedLoan
                    )
                )

                Toast.makeText(this, getString(R.string.add_item_success), Toast.LENGTH_SHORT).show()

                onBackPressed()
            }
        }else{
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("لطفا ابتدا یک وام ایجاد کنید.")
            dialog.setCancelable(false)
            dialog.setNegativeButton("باشه") { dialog, which ->
                onBackPressed()
            }
            dialog.show()
        }

    }
}