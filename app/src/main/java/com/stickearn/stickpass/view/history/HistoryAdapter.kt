package com.stickearn.stickpass.view.history

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.model.CartMdl
import com.stickearn.stickpass.model.HistoryMdl
import com.stickearn.stickpass.view.mart.CartAdapter
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.model.PointHistoryResponseMdl
import com.stickearn.stickpass.model.PointResponseMdl


/**
 * Created by oohyugi on 2/1/18.
 */
class HistoryAdapter(private val context : Context, private val list : List<PointHistoryResponseMdl>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var tvName:TextView
        var tvPoint:TextView
        var tvDesc:TextView
        var tvDate:TextView
        var imgType:ImageView
        init {

            tvName = itemView.findViewById(R.id.tvTitle)
            tvPoint = itemView.findViewById(R.id.tvPoint)
            tvDesc = itemView.findViewById(R.id.tvDesc)
            tvDate = itemView.findViewById(R.id.tvDate)
            imgType = itemView.findViewById(R.id.imgType)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ViewHolder {
        val view : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_history, parent, false)

        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item:PointHistoryResponseMdl= list[position]
        holder.tvName.text = item.source.capitalize()
        holder.tvDate.text = " - ${DateHelper.convertDateFromApi(item.createdAt,DateHelper.DATE_OUTPUT_v1)}"
        holder.tvDesc.text = item.action

        if (item.typeTransaction.equals("credit")){
            holder.tvPoint.setTextColor(context.resources.getColorStateList(R.color.colorGreen600))
            holder.tvPoint.text = "+${item.amount} point"
        }else{
            holder.tvPoint.text = "-${item.amount} point"
            holder.tvPoint.setTextColor(context.resources.getColorStateList(R.color.colorRed))
        }
        if (item.source=="survey"){
//            setIconColor(holder.imgType,R.color.colorGreen600)
//            holder.imgType.backgroundTintList(ContextCompat.getColor(context,R.color.colorGreen600))
            holder.imgType.setBackgroundResource(R.drawable.shape_circle_green)
            holder.imgType.setImageResource(R.drawable.ic_survey_done)
        }
        else if (item.source=="mart"){
//            setIconColor(holder.imgType,R.color.colorAccent)
            holder.imgType.setBackgroundResource(R.drawable.shape_circle_accent)
            holder.imgType.setImageResource(R.drawable.ic_shopping_bag)
        }
//        else if (item.type==3){
//            holder.imgType.setBackgroundResource(R.drawable.shape_circle_primary)
//            holder.imgType.setImageResource(R.drawable.ic_voucher)
//        }

    }

    fun setIconColor(imageView: ImageView, color: Int) {
        imageView.drawable.mutate()
        imageView.drawable.setColorFilter(ContextCompat.getColor(imageView.context, color), PorterDuff.Mode.SRC_ATOP)
    }
}