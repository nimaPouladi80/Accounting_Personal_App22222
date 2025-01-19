package com.example.personalcalculatingapplication.ui.payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.adapters.PaymentItemsAdapter
import com.example.personalcalculatingapplication.getBaseDiff
import com.example.personalcalculatingapplication.sql.CalculatingDatabaseHelper
import com.example.personalcalculatingapplication.sql.Payment
import com.example.personalcalculatingapplication.sql.G
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import kotlinx.android.synthetic.main.layout_list.*
import java.util.*
import kotlin.collections.ArrayList

class PaymentListActivity: AppCompatActivity() , PaymentItemsAdapter.AdapterListener{

    lateinit var item: CalculatingDatabaseHelper
    var selectedFirstDate = ""
    var selectedSecondDate = ""
    lateinit var mList: ArrayList<Payment>
    lateinit var adapter: PaymentItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_list)

        val subject = intent.getStringExtra("SUBJECT")

        tv_title.text = "گزارش $subject ها"
          item = CalculatingDatabaseHelper(this , G.DBNAME , 1)

        bt_back.setOnClickListener { onBackPressed() }

        card_filter_first.setOnClickListener {
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

                    selectedFirstDate = "$year-$selectedMonth-$selectedDay 00:00:00"


                    tv_selected_date_first.text =
                        year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth

                    bt_remove_date_first.visibility = View.VISIBLE

                    if (selectedSecondDate.isNotEmpty())
                        getDiffList()
                },
                persianCalendar.persianYear,
                persianCalendar.persianMonth,
                persianCalendar.persianDay
            )
            datePickerDialog.show(
                fragmentManager,
                getString(R.string.app_name)
            )
        }

        card_filter_second.setOnClickListener {
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

                    selectedSecondDate = "$year-$selectedMonth-$selectedDay 00:00:00"

                    tv_selected_date_second.text =
                        year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth

                    bt_remove_date_second.visibility = View.VISIBLE

                    if (selectedFirstDate.isNotEmpty())
                        getDiffList()
                },
                persianCalendar.persianYear,
                persianCalendar.persianMonth,
                persianCalendar.persianDay
            )
            datePickerDialog.show(
                 fragmentManager,
                getString(R.string.app_name)
            )
        }

         bt_remove_date_first.setOnClickListener {
            selectedFirstDate = ""
            bt_remove_date_first.visibility = View.GONE
            tv_selected_date_first.text = "تاریخ شروع"
             adapter.setData(mList)
        }
         bt_remove_date_second.setOnClickListener {
            selectedSecondDate = ""
            bt_remove_date_second.visibility = View.GONE
            tv_selected_date_second.text = "تاریخ پایان"
             adapter.setData(mList)
        }


       mList= item.getAllPayment() as ArrayList<Payment>
      rcv_items.layoutManager = LinearLayoutManager(this)
        adapter = PaymentItemsAdapter(this)
        rcv_items.adapter = adapter

        if(mList.isEmpty()){
            ll_empty.visibility = View.VISIBLE
            rcv_items.visibility = View.GONE
        }else{
            ll_empty.visibility = View.GONE
            rcv_items.visibility = View.VISIBLE

            rcv_items.layoutManager = LinearLayoutManager(this)
            adapter.setData(mList)
        }



    }

    private fun getDiffList() {
        val finalList = ArrayList<Payment>()
        if (selectedFirstDate.isNotEmpty()) {
            if (selectedSecondDate.isNotEmpty()) {
                // has both date
                for (i in mList) {
                    val cDate = i.date.replace("/", "-")+" 00:00:00"

                    if (getBaseDiff(cDate, selectedFirstDate) >= 0 && getBaseDiff(
                            selectedSecondDate,
                            cDate
                        ) >= 0
                    ) {
                        finalList.add(i)
                    }
                }

                adapter.setData(finalList)
            } else {
                Toast.makeText(this, getString(R.string.select_second_date), Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this, getString(R.string.select_first_date), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDeleteItemClick(data: Payment) {
        item.deletePayment(data)
        refreshList()
    }

    private fun refreshList() {
     runOnUiThread {
            val list = item.getAllPayment()

            if (list.isEmpty()) {
                ll_empty.visibility = View.VISIBLE
                rcv_items.visibility = View.GONE
            } else {
                ll_empty.visibility = View.GONE
                rcv_items.visibility = View.VISIBLE

                adapter.setData(list)
            }
        }
    }
}