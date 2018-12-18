package com.stickearn.stickpass.view.voucher.listvoucher

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.model.VoucherMdl
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformation
import com.stickearn.stickpass.view.voucher_detail.VoucherDetailActivity

/**
 * Created by macos-vanya on 07/02/18.
 */
class VoucherAdapter(private val context : Activity, private val list : List<VoucherMdl>, val onClickItem: OnClickItem): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

        holder.tvName.text = item.namaVoucher
        holder.tvPoint.text = item.amount.toString()
        holder.tvValid.text = "${context.getString(R.string.valid_until)} ${DateHelper.convertDateFromApi(item.validtodate,DateHelper.DATE_OUTPUT_v1)}"
        holder.imgType.loadImageWithPicassoTransformation(item.image.toString())

        holder.btnPurchased.setOnClickListener {

            onClickItem.onClickPurchased(item,holder.progressBar)
        }
        holder.imgType.setOnClickListener{

            VoucherDetailActivity().startThisActivity(context,item,1)
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

            view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_list_voucher, parent, false)
            return ItemHolder(view)
        }else{
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_loadmore, parent, false)
            return LoadingHolder(view)
        }


    }
    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView
        var tvPoint:TextView
        var imgType: ImageView
        var tvValid:TextView
        var btnPurchased:Button
        var progressBar:ProgressBar
        var lyPoint: LinearLayout = itemView.findViewById(R.id.lyPoint)

        init {

            tvName = itemView.findViewById(R.id.tv_redeem_title)
            tvPoint = itemView.findViewById(R.id.tv_point_redeem)
            tvValid = itemView.findViewById(R.id.tvValid)
            imgType = itemView.findViewById(R.id.iv_redeem)
            btnPurchased = itemView.findViewById(R.id.btnRedeem)
            progressBar = itemView.findViewById(R.id.progressBar)

        }

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