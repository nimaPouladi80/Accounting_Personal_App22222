package com.example.personalcalculatingapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.adapters.*
import com.example.personalcalculatingapplication.sql.*
import com.example.personalcalculatingapplication.ui.entire.AddEntireActivity
import com.example.personalcalculatingapplication.ui.income.AddIncomeActivity
import com.example.personalcalculatingapplication.ui.loan.AddLoanActivity
import com.example.personalcalculatingapplication.ui.payment.AddPaymentActivity
import com.example.personalcalculatingapplication.ui.chek.AddChekActivity
import kotlinx.android.synthetic.main.layout_home.*
import kotlinx.android.synthetic.main.layout_home.view.*


class HomeFragment : Fragment(), ChekItemsAdapter.AdapterListener,
    IncomeItemsAdapter.AdapterListener, EntireItemsAdapter.AdapterListener,
    LoanItemsAdapter.AdapterListener, PaymentItemsAdapter.AdapterListener {

    var listener: OnHomeFragmentInteractionListener? = null
    private lateinit var mContext: Context

    lateinit var mView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.layout_home, container, false)
        iniUI()
        return mView
    }

    lateinit var item: CalculatingDatabaseHelper
    var selectedFirstDate = ""
    var selectedSecondDate = ""


    private fun iniUI() {
        item = CalculatingDatabaseHelper(mContext, G.DBNAME, 1)

       /* mView.card_filter_first.setOnClickListener {
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

                    selectedFirstDate = "$year-$selectedMonth-$selectedDay 00:00:00"


                    mView.tv_selected_date_first.text =
                        year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth

                    mView.bt_remove_date_first.visibility = View.VISIBLE

                    if (selectedSecondDate.isNotEmpty())
                        getDiffList()
                },
                persianCalendar.persianYear,
                persianCalendar.persianMonth,
                persianCalendar.persianDay
            )
            datePickerDialog.show(
                (mContext as Activity).fragmentManager,
                getString(R.string.app_name)
            )
        }
        mView.card_filter_second.setOnClickListener {
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

                    selectedSecondDate = "$year-$selectedMonth-$selectedDay 00:00:00"

                    mView.tv_selected_date_second.text =
                        year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth

                    mView.bt_remove_date_second.visibility = View.VISIBLE

                    if (selectedFirstDate.isNotEmpty())
                        getDiffList()
                },
                persianCalendar.persianYear,
                persianCalendar.persianMonth,
                persianCalendar.persianDay
            )
            datePickerDialog.show(
                (mContext as Activity).fragmentManager,
                getString(R.string.app_name)
            )
        }*/
        /* mView.bt_remove_date_first.setOnClickListener {
             selectedFirstDate = ""
             mView.bt_remove_date_first.visibility = View.GONE
             mView.tv_selected_date_first.text = "تاریخ شروع"
             adapter.setData(mList)
         }
         mView.bt_remove_date_second.setOnClickListener {
             selectedSecondDate = ""
             mView.bt_remove_date_second.visibility = View.GONE
             mView.tv_selected_date_second.text = "تاریخ پایان"
             adapter.setData(mList)
         }*/

        mView.fab_check.setOnClickListener {
            startActivity(Intent(mContext, AddChekActivity::class.java).putExtra("SUBJECT", "چک"))
            mView.right_labels.toggle()
        }
        mView.fab_cost.setOnClickListener {
            startActivity(Intent(mContext, AddEntireActivity::class.java).putExtra("SUBJECT", "هزینه"))
            mView.right_labels.toggle()
        }
        mView.fab_income.setOnClickListener {
            startActivity(Intent(mContext, AddIncomeActivity::class.java).putExtra("SUBJECT", "درآمد"))
            mView.right_labels.toggle()
        }
        mView.fab_loan.setOnClickListener {
            startActivity(Intent(mContext, AddLoanActivity::class.java).putExtra("SUBJECT", "وام"))
            mView.right_labels.toggle()
        }
        mView.fab_payment.setOnClickListener {
            startActivity(Intent(mContext, AddPaymentActivity::class.java).putExtra("SUBJECT", "قسط"))
            mView.right_labels.toggle()
        }

        refreshList()

    }
    /*    private fun getDiffList() {
        val finalList = ArrayList<Data>()
        if (selectedFirstDate.isNotEmpty()) {
            if (selectedSecondDate.isNotEmpty()) {
                // has both date
                for (i in mList) {
                    val cDate = i.date.replace("/", "-") + " 00:00:00"

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
                Toast.makeText(mContext, getString(R.string.select_second_date), Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(mContext, getString(R.string.select_first_date), Toast.LENGTH_SHORT)
                .show()
        }
    }*/


    override fun onResume() {
        super.onResume()
        if (::item.isInitialized) {
            refreshList()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
        /*if (context is OnHomeFragmentInteractionListener) {
            listener = context
            this.mContext = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnHomeFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnHomeFragmentInteractionListener {
    }

    companion object {

        @JvmStatic
        fun newInstance(
        ) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }




    override fun onDeleteItemClick(data: Chek) {
        item.deleteChek(data)
        refreshList()
    }
    override fun onDeleteItemClick(data: Income) {
        item.deleteIncome(data)
        refreshList()
    }
    override fun onDeleteItemClick(data: Entire) {
        item.deleteEntire(data)
        refreshList()
    }
    override fun onDeleteItemClick(data: Loan) {
        item.deleteLoan(data)
        refreshList()
    }
    override fun onDeleteItemClick(data: Payment) {
        item.deletePayment(data)
        refreshList()
    }




    private fun refreshList() {
        (mContext as MainActivity).runOnUiThread {

            val db = CalculatingDatabaseHelper(mContext, G.DBNAME , 1)
            val incomes = db.getAllIncome()
            if (incomes.isEmpty()){
                mView.img_income.visibility = View.GONE
                mView.tv_income.visibility = View.GONE
            }else{
                mView.img_income.visibility = View.VISIBLE
                mView.tv_income.visibility = View.VISIBLE

                var sum = 0
                for(i in incomes){
                    sum += i.price.toInt()
                }

                mView.tv_income.text= "درآمدها: $sum تومان"
            }

            val entires= db.getAllEntire()
            if (entires.isEmpty()){
                mView.img_entire.visibility = View.GONE
                mView.tv_entire.visibility = View.GONE
            }else{
                mView.img_entire.visibility = View.VISIBLE
                mView.tv_entire.visibility = View.VISIBLE

                var sum = 0
                for(i in entires){
                    sum += i.price.toInt()
                }
                mView.tv_entire.text = "هزینه ها: $sum تومان"
            }


            mView.rcv_cheks.isNestedScrollingEnabled = false
            mView.rcv_income.isNestedScrollingEnabled = false
            mView.rcv_entire.isNestedScrollingEnabled = false
            mView.rcv_loans.isNestedScrollingEnabled = false
            mView.rcv_payment.isNestedScrollingEnabled = false


            val chekList = item.getAllCheks()
            val incomeList = item.getAllIncome()
            val entireList = item.getAllEntire()
            val loanList = item.getAllLoans()
            val paymentList = item.getAllPayment()

            if (chekList.isEmpty() && incomeList.isEmpty() && entireList.isEmpty() && loanList.isEmpty() && paymentList.isEmpty()) {
                mView.ll_empty.visibility = View.VISIBLE

                mView.payment_title_bar.visibility = View.GONE
                mView.rcv_payment.visibility = View.GONE

                mView.loan_title_bar.visibility = View.GONE
                mView.rcv_loans.visibility = View.GONE

                mView.entire_title_bar.visibility = View.GONE
                mView.rcv_entire.visibility = View.GONE

                mView.income_title_bar.visibility = View.GONE
                mView.rcv_income.visibility = View.GONE

                mView.check_title_bar.visibility = View.GONE
                mView.rcv_cheks.visibility = View.GONE
            }else{
                mView.ll_empty.visibility = View.GONE
                if (chekList.isNotEmpty()) {
                    mView.check_title_bar.visibility = View.VISIBLE
                    mView.rcv_cheks.visibility = View.VISIBLE

                    mView.rcv_cheks.layoutManager = LinearLayoutManager(mContext)
                    val adapter = ChekItemsAdapter(this)
                    mView.rcv_cheks.adapter = adapter
                    adapter.setData(chekList)
                } else {
                    mView.check_title_bar.visibility = View.GONE
                    mView.rcv_cheks.visibility = View.GONE
                }
                if (incomeList.isNotEmpty()) {
                    mView.income_title_bar.visibility = View.VISIBLE
                    mView.rcv_income.visibility = View.VISIBLE

                    mView.rcv_income.layoutManager = LinearLayoutManager(mContext)
                    val adapter =  IncomeItemsAdapter(this)
                    mView.rcv_income.adapter =adapter
                    adapter.setData(incomeList)
                } else {
                    mView.income_title_bar.visibility = View.GONE
                    mView.rcv_income.visibility = View.GONE
                }
                if (entireList.isNotEmpty()) {
                    mView.entire_title_bar.visibility = View.VISIBLE
                    mView.rcv_entire.visibility = View.VISIBLE

                    mView.rcv_entire.layoutManager = LinearLayoutManager(mContext)
                    val adapter =  EntireItemsAdapter(this)
                    mView.rcv_entire.adapter =adapter
                    adapter.setData(entireList)
                } else {
                    mView.entire_title_bar.visibility = View.GONE
                    mView.rcv_entire.visibility = View.GONE
                }
                if (loanList.isNotEmpty()) {
                    mView.loan_title_bar.visibility = View.VISIBLE
                    mView.rcv_loans.visibility = View.VISIBLE

                    mView.rcv_loans.layoutManager = LinearLayoutManager(mContext)
                    val adapter = LoanItemsAdapter(this)
                    mView.rcv_loans.adapter = adapter
                    adapter.setData(loanList)
                } else {
                    mView.loan_title_bar.visibility = View.GONE
                    mView.rcv_loans.visibility = View.GONE
                }
                if (paymentList.isNotEmpty()) {
                    mView.payment_title_bar.visibility = View.VISIBLE
                    mView.rcv_payment.visibility = View.VISIBLE

                    mView.rcv_payment.layoutManager = LinearLayoutManager(mContext)
                    val adapter =  PaymentItemsAdapter(this)
                    mView.rcv_payment.adapter =adapter
                    adapter.setData(paymentList)
                } else {
                    mView.payment_title_bar.visibility = View.GONE
                    mView.rcv_payment.visibility = View.GONE
                }
            }

        }
    }


}
