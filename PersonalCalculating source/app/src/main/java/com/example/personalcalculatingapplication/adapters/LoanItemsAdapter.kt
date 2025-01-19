package com.example.personalcalculatingapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.sql.Loan
import kotlinx.android.synthetic.main.view_items.view.*
import java.util.*
import kotlin.collections.ArrayList

class LoanItemsAdapter(val listener: AdapterListener): RecyclerView.Adapter<LoanItemsAdapter.MyViewHolder>() {

    var mList = ArrayList<Loan>()

    public fun setData( mList: List<Loan>)  {
        Collections.reverse(mList)
        this.mList = mList as ArrayList<Loan>
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_items , parent , false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = mList[position]

        holder.itemView.tv_date.text = item.date
        holder.itemView.tv_cost.text = "مبلغ وام: ${item.price}"
        holder.itemView.tv_desc.text = item.desc

        holder.itemView.ll_delete.setOnClickListener {
            listener.onDeleteItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    public  interface AdapterListener{
        fun onDeleteItemClick(item:Loan)
    }
}