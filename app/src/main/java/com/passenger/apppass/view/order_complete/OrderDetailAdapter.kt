package com.stickearn.stickpass.view.order_complete

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.DataOrderDetailMdl
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformationMartItem

/**
 * Created by macos-vanya on 03/04/18.
 */
class OrderDetailAdapter (private val context : Context, private val list : List<DataOrderDetailMdl>) : RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_product_name: TextView
        var tv_product_qty: TextView

        init {
            tv_product_name = itemView.findViewById(R.id.tv_product_name)
            tv_product_qty = itemView.findViewById(R.id.tv_product_qty)

        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, type : Int) : OrderDetailAdapter.ViewHolder{
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_order_detail, parent, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder : OrderDetailAdapter.ViewHolder, position : Int){
        var item : DataOrderDetailMdl = list.get(position)
        holder.tv_product_name.text = item.product_name
        holder.tv_product_qty.text = "${item.quantity}"
    }

    override fun getItemCount() : Int{
        return list.size
    }
}