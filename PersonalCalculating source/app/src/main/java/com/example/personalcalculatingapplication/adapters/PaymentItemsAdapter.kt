package com.example.personalcalculatingapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.sql.Payment
import kotlinx.android.synthetic.main.view_payment_items.view.*
import java.util.*
import kotlin.collections.ArrayList

class PaymentItemsAdapter(val listener: AdapterListener): RecyclerView.Adapter<PaymentItemsAdapter.MyViewHolder>() {

    var mList = ArrayList<Payment>()

    public fun setData( mList: List<Payment>)  {
        Collections.reverse(mList)
        this.mList = mList as ArrayList<Payment>
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_payment_items , parent , false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = mList[position]

        holder.itemView.tv_subject.text = item.subject
        holder.itemView.tv_date.text = item.date
        holder.itemView.tv_cost.text = "مبلغ کل: ${item.price}"
        holder.itemView.tv_ech_cost.text = "مبلغ هر قسط: ${item.price}"
        holder.itemView.tv_count.text = "تعداد اقساط: ${item.count}"
        holder.itemView.tv_loan_name.text = "وام: ${item.paymentLoan}"
        if (item.desc.isEmpty()){
            holder.itemView.tv_desc.visibility = View.GONE
        }else{
            holder.itemView.tv_desc.visibility = View.VISIBLE
            holder.itemView.tv_desc.text = item.desc
        }

        holder.itemView.ll_delete.setOnClickListener {
            listener.onDeleteItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    public  interface AdapterListener{
        fun onDeleteItemClick(item:Payment)
    }
}