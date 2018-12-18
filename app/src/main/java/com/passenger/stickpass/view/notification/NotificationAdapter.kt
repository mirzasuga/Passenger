package com.stickearn.stickpass.view.notification

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.model.NotificationResponseMdl
import com.stickearn.stickpass.view.notif_detail.NotifDetailActivity
import com.stickearn.stickpass.view.survey_question.QuestionAdapter


/**
 * Created by oohyugi on 2/26/18.
 */
class NotificationAdapter(val context:Context, val list:List<NotificationResponseMdl>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val VIEW_ITEM = 1
    val VIEW_LOADING = 0
    var isMoreLoading = false
    lateinit var mList: List<NotificationResponseMdl>
    init {
        mList = list
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {


        if (holder is ItemHolder){
            val item = list[position]
                initDataItem(holder,item)
        }else if (holder is LoadingHolder){
            initDataLoading(holder)
        }

    }

    private fun initDataLoading(holder: LoadingHolder?) {
        if (isMoreLoading){
            holder!!.progressBar!!.visibility =View.VISIBLE
        }else{
            holder!!.progressBar!!.visibility =View.GONE
        }

    }

    private fun initDataItem(holder: ItemHolder, item: NotificationResponseMdl) {
        holder.tvTitle.text = item.subject
        holder.tvDate.text = DateHelper.convertDateFromApi(item.createdAt!!,DateHelper.DATE_OUTPUT_v1)
        if (item.read==0){
            holder.imgUnread.visibility = View.VISIBLE
        }else{
            holder.imgUnread.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            NotifDetailActivity().startThisActivity(context,item.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val view : View
        if (viewType==VIEW_ITEM){

            view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_notification_new, parent, false)
            return ItemHolder(view)
        }else{
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_loadmore, parent, false)
            return LoadingHolder(view)
        }




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



    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle = itemView!!.findViewById<TextView>(R.id.tvTitle)
        var tvDate = itemView!!.findViewById<TextView>(R.id.tvDate)
        var imgUnread = itemView!!.findViewById<ImageView>(R.id.imgUnread)

    }
    class LoadingHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
       var progressBar: ProgressBar? = itemView!!.findViewById(R.id.progressbar)

    }



}




