package com.example.personalcalculatingapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.personalcalculatingapplication.PreferenceHelper
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.sql.CalculatingDatabaseHelper
import com.example.personalcalculatingapplication.sql.G
import kotlinx.android.synthetic.main.layout_add.view.*
import kotlinx.android.synthetic.main.layout_add.view.btSave
import kotlinx.android.synthetic.main.layout_profile.*
import kotlinx.android.synthetic.main.layout_profile.view.*


class ProfileFragment : Fragment() {

    var listener: OnProfileFragmentInteractionListener? = null
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
        mView = inflater.inflate(R.layout.layout_profile, container, false)

        val preferenceHelper = PreferenceHelper(mContext)

        mView.et_name.setText(preferenceHelper.getUserName())

        mView.btSave.setOnClickListener {
            val name  = mView.et_name.text.toString().trim()

            if (name.isEmpty()){
                mView.et_name.error ="لطفا مقدار نام را وارد کنید"
                return@setOnClickListener
            }

            preferenceHelper.setUserName(name)
            Toast.makeText(mContext , getString(R.string.update_name_success) , Toast.LENGTH_LONG).show()
        }

        mView.    tv_aboutus.setOnClickListener {
            startActivity(Intent(mContext , AboutUsActivity::class.java))
        }

        mView. tv_exit.setOnClickListener {
            preferenceHelper.exit()
            val item = CalculatingDatabaseHelper(mContext, G.DBNAME, 1)
            item.deleteTable()
            startActivity(Intent(mContext , LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }

        return mView
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
        /* if (context is OnProfileFragmentInteractionListener) {
             listener = context
             this.mContext = context
         } else {
             throw RuntimeException(context.toString() + " must implement OnProfileFragmentInteractionListener")
         }*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnProfileFragmentInteractionListener {
    }


    companion object {
        @JvmStatic
        fun newInstance(
        ) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
