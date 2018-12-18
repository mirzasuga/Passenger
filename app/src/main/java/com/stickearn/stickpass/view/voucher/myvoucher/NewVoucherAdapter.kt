package com.stickearn.stickpass.view.voucher.myvoucher

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.model.VoucherMdl
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformation
import com.stickearn.stickpass.view.voucher_detail.VoucherDetailActivity

/**
 * Created by macos-vanya on 07/02/18.
 */
class NewVoucherAdapter(private val context : Activity, private val list : List<VoucherMdl>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_ITEM = 1
    val VIEW_LOADING = 0
    var isMoreLoading = false

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ItemHolder){
            val item = list[position]
            initDataItem(holder,item)
        }else if (holder is LoadingHolder){
            initDataLoading(holder)
        }
    }

    private fun initDataLoading(holder: LoadingHolder) {
        if (isMoreLoading){
            holder!!.progressBar!!.visibility =View.VISIBLE
        }else{
            holder!!.progressBar!!.visibility =View.GONE
        }

    }

    private fun initDataItem(holder: ItemHolder, item: VoucherMdl) {

        holder.tvName.text = item.codeVoucher
        holder.tvDate.text = DateHelper.convertDateFromApi(item.validtodate,DateHelper.DATE_OUTPUT_v1)
        holder.imgType.loadImageWithPicassoTransformation(item.image.toString())
        /**
         * TODO URL IMAGE
         */
//        holder.imgType.loadImageWithPicassoTransformation("belum ada")
//        holder.imgType.setImageResource(item.thumbnail)
        holder.itemView.setOnClickListener {
            VoucherDetailActivity().startThisActivity(context,item,2)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*val id:Int?=0,
        val name:String?=null,
        val description:String?=null,
        val point:Int?=0,
        val date:String?=null,
        val type:Int?=0*/


    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder?  {
        val view : View
        if (viewType==VIEW_ITEM){

            view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_list_myvoucher, parent, false)
            return ItemHolder(view)
        }else{
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_loadmore, parent, false)
            return LoadingHolder(view)
        }


    }
    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tv_voucher_title)
        var tvDate: TextView =itemView.findViewById(R.id.tv_date_voucher)
        var imgType: ImageView = itemView.findViewById(R.id.iv_voucher)
        var lyPoint: LinearLayout = itemView.findViewById(R.id.lyPoint)


    }
    class LoadingHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar? = itemView!!.findViewById(R.id.progressbar)

    }

    override fun getItemCount(): Int {
        return list.size+ if (isMoreLoading) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (isMoreLoading && position == list.size) {
            VIEW_LOADING
        } else {
            VIEW_ITEM
        }
    }

    interface OnClickItem{
        fun onClickPurchased(item: VoucherMdl, progressBar: ProgressBar)
    }


}