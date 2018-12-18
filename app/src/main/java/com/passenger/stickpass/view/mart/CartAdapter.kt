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
import com.stickearn.stickpass.model.CartMdl
import com.stickearn.stickpass.utils.loadImageWithPicassoFit
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformationMartItem

/**
 * Created by oohyugi on 1/24/18.
 */
class CartAdapter (private val context : Context, private val list : List<CartMdl>, val onItemClickListener: OnCartItemClickListener) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    var totalPrice:Int = 0
    var totalPoint:Int = 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleTextView: TextView
        var priceTextView: TextView
        var qtyTextView : TextView
        var plusImageButton: ImageButton
        var minImageButton: ImageButton
        var thumbImageView : ImageView

        init {
            titleTextView = itemView.findViewById(R.id.tv_cart_name)
            priceTextView = itemView.findViewById(R.id.tv_cart_price)
            qtyTextView = itemView.findViewById(R.id.tv_cart_qty)
            plusImageButton = itemView.findViewById(R.id.btnPlus)
            minImageButton = itemView.findViewById(R.id.btnMin)
            thumbImageView = itemView.findViewById(R.id.imgThumb)

        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, type : Int) : CartAdapter.ViewHolder{
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder : CartAdapter.ViewHolder, position : Int){
        var item : CartMdl = list.get(position)
        holder.titleTextView.text = item.productName
        holder.priceTextView.text = "${item.pricePoint}pts / ${StringHelper.indonesiaFormat(item.priceCurrency.toDouble())}"
        holder.qtyTextView.text=item.qty.toString()
        holder.thumbImageView.loadImageWithPicassoTransformationMartItem(item.imgUrl!!)

        var qty =item.qty

        holder.plusImageButton.setOnClickListener({
            if (qty!=item.stock){
                qty++
                totalPrice = item.priceCurrency*qty
                totalPoint = item.pricePoint*qty
                holder.qtyTextView.text=qty.toString()
                onItemClickListener.onCartItemClickPlus(position,item,qty,totalPrice,totalPoint)
            }




        })
        holder.minImageButton.setOnClickListener {

            if (qty!=0){
                qty--
                totalPrice = item.priceCurrency*qty
                totalPoint = item.pricePoint*qty
                holder.qtyTextView.text=qty.toString()
                onItemClickListener.onCartItemClickMinus(position,item,qty,totalPrice,totalPoint)
            }
        }





    }

    override fun getItemCount() : Int{
        return list.size
    }

    interface OnCartItemClickListener {
        fun onCartItemClickPlus(position: Int,data: CartMdl,qty:Int, totalPrice: Int, totalPoint: Int)
        fun onCartItemClickMinus(position: Int, data: CartMdl, qty:Int, totalPrice: Int, totalPoint: Int)
    }
}