package com.example.personalcalculatingapplication.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.personalcalculatingapplication.R


class BudgetFragment : Fragment() {

    var listener: OnBudgetFragmentInteractionListener? = null
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
        mView = inflater.inflate(R.layout.layout_budget, container, false)

        return mView
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
       /* if (context is OnBudgetFragmentInteractionListener) {
            listener = context
            this.mContext = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnBudgetFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnBudgetFragmentInteractionListener {
    }


    companion object {

        @JvmStatic
        fun newInstance(
        ) =
            BudgetFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
