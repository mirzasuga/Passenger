package com.stickearn.stickpass.view.mart

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
import com.stickearn.stickpass.model.MartMdl
import com.stickearn.stickpass.utils.loadImageWithPicassoFit
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformation
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformationMartItem
import com.stickearn.stickpass.view.mart_detail.MartDetailActivity


/**
 * Created by macos-vanya on 23/01/18.
 */
class MartAdapter (private val context : Context, private val list : List<MartMdl>, val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MartAdapter.ViewHolder>(){

    var stock:Int = 0
    // var qty:Int = 0
    var totalPrice:Int = 0
    var totalPoint:Int = 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleTextView: TextView
        var priceTextView: TextView
        var qtyTextView: TextView
        var plusImageButton: ImageButton
        var minImageButton: ImageButton
        var thumbImageView : ImageView

        init {
            titleTextView = itemView.findViewById(R.id.tv_mart_name)
            priceTextView = itemView.findViewById(R.id.tv_mart_price)
            qtyTextView = itemView.findViewById(R.id.tv_mart_qty)
            thumbImageView = itemView.findViewById(R.id.iv_mart)
            plusImageButton = itemView.findViewById(R.id.ib_mart_plus)
            minImageButton = itemView.findViewById(R.id.ib_mart_min)
        }
    }
    override fun onCreateViewHolder(parent : ViewGroup, type : Int) : MartAdapter.ViewHolder{
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_mart, parent, false);

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder : MartAdapter.ViewHolder, position : Int){
        var item : MartMdl = list.get(position)
        holder.titleTextView.text = item.productName
        holder.priceTextView.text = "${item.pricePoint}pts / ${StringHelper.indonesiaFormat(item.priceCurrency.toDouble())}"
        holder.qtyTextView.text=item.qty.toString()
        holder.thumbImageView.loadImageWithPicassoTransformationMartItem(item.imgUrl!!)
        var qty=item.qty

        holder.plusImageButton.setOnClickListener({
            if (qty!=item.stock){
                qty++
                totalPrice = item.priceCurrency*qty
                totalPoint = item.pricePoint*qty
                onItemClickListener.onItemClickPlus(position,item,qty,totalPrice,totalPoint)
                holder.qtyTextView.text=qty.toString()

                holder.minImageButton.setImageResource(R.drawable.ic_min_box)
                holder.plusImageButton.setImageResource(R.drawable.ic_add_box)
            }else{
                holder.plusImageButton.setImageResource(R.drawable.ic_add_box_gray)
            }

        })

        holder.minImageButton.setOnClickListener {

            if (qty!=0) {
                qty--
                totalPrice = item.priceCurrency * qty
                totalPoint = item.pricePoint * qty
                onItemClickListener.onItemClickMinus(position, item, qty, totalPrice, totalPoint)
                holder.qtyTextView.text = qty.toString()


            }
        }

        holder.thumbImageView.setOnClickListener{
            onItemClickListener.onClickItem(position,item)
//
        }
        if (qty >=1 && qty!=item.stock) {
            holder.minImageButton.setImageResource(R.drawable.ic_min_box)
            holder.plusImageButton.setImageResource(R.drawable.ic_add_box)
        }else if (qty==0){

            holder.minImageButton.setImageResource(R.drawable.ic_min_grey)
            holder.plusImageButton.setImageResource(R.drawable.ic_add_box)
        }else if (qty == item.stock){
            holder.plusImageButton.setImageResource(R.drawable.ic_add_box_gray)
        }
    }

    override fun getItemCount() : Int{
        return list.size
    }

    interface OnItemClickListener {
        //        fun onItemClick(position: Int,data:MartMdl,clickPlus:ImageButton,clickMinus:ImageButton,tvQty:TextView)
        fun onItemClickPlus(position: Int, data: MartMdl, qty: Int, totalPrice: Int, totalPoint: Int)
        fun onItemClickMinus(position: Int,data: MartMdl,qty:Int, totalPrice: Int, totalPoint: Int)
        fun onClickItem(position: Int,data: MartMdl)
    }
}