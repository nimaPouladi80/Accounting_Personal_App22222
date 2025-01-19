package com.example.personalcalculatingapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.ui.chek.ChekListActivity
import com.example.personalcalculatingapplication.ui.entire.EntireListActivity
import com.example.personalcalculatingapplication.ui.income.IncomeListActivity
import com.example.personalcalculatingapplication.ui.loan.LoanListActivity
import com.example.personalcalculatingapplication.ui.payment.PaymentListActivity
import kotlinx.android.synthetic.main.layout_report.view.*


class ReportFragment : Fragment() {

    var listener: OnReportFragmentInteractionListener? = null
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
        mView = inflater.inflate(R.layout.layout_report, container, false)



        mView.card_check.setOnClickListener {
            startActivity(Intent(mContext , ChekListActivity::class.java ).putExtra("SUBJECT" , "چک"))
        }
        mView.card_loan.setOnClickListener {
            startActivity(Intent(mContext , LoanListActivity::class.java ).putExtra("SUBJECT" , "وام"))
        }
        mView.card_income.setOnClickListener {
            startActivity(Intent(mContext , IncomeListActivity::class.java ).putExtra("SUBJECT" , "درآمد"))
        }
        mView.card_cost.setOnClickListener {
            startActivity(Intent(mContext , EntireListActivity::class.java ).putExtra("SUBJECT" , "هزینه"))
        }
        mView.card_payment.setOnClickListener {
            startActivity(Intent(mContext , PaymentListActivity::class.java ).putExtra("SUBJECT" , "اقساط"))
        }

        return mView
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
       /* if (context is OnReportFragmentInteractionListener) {
            listener = context
            this.mContext = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnReportFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnReportFragmentInteractionListener {
    }


    companion object {

        @JvmStatic
        fun newInstance(
        ) =
            ReportFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
