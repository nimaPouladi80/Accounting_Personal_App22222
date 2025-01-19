package com.example.personalcalculatingapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalcalculatingapplication.R
import com.example.personalcalculatingapplication.sql.Chek
import kotlinx.android.synthetic.main.view_check_items.view.*
import java.util.*
import kotlin.collections.ArrayList

class ChekItemsAdapter(val listener: AdapterListener): RecyclerView.Adapter<ChekItemsAdapter.MyViewHolder>() {

    var mList = ArrayList<Chek>()

    public fun setData( mList: List<Chek>)  {
        Collections.reverse(mList)
        this.mList = mList as ArrayList<Chek>
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_check_items , parent , false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = mList[position]

        holder.itemView.tv_created_date.text = "صدور: ${item.createdDate}"
        holder.itemView.tv_end_date.text = "سررسید: ${item.endDate}"
        holder.itemView.tv_cost.text = "مبلغ: ${item.price}"
        if (item.desc.isEmpty()){
            holder.itemView.tv_desc.visibility = View.GONE
        }else{
            holder.itemView.tv_desc.visibility = View.VISIBLE
            holder.itemView.tv_desc.text = item.desc
        }
        holder.itemView.tv_serial.text = "شماره سریال: ${item.serial}"
        holder.itemView.tv_status.text = "وضعیت: ${item.status}"

        holder.itemView.ll_delete.setOnClickListener {
            listener.onDeleteItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    public  interface AdapterListener{
        fun onDeleteItemClick(item:Chek)
    }
}