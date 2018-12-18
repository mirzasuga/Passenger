package com.stickearn.stickpass.view.notification

import android.widget.ProgressBar
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.model.NotificationResponseMdl


/**
 * Created by oohyugi on 3/20/18.
 */
class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var items: MutableList<NotificationResponseMdl> = mutableListOf()

    private val VIEW_ITEM = 1

    private val VIEW_LOADMORE = 0

    private var isMoreLoading = false

    private val visibleThreshold = 10

    internal var lasttVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0

    var linearLayoutManager: LinearLayoutManager? = null

    var onLoadMoreListener: OnLoadMoreListener? = null

    private var recyclerView: RecyclerView? = null

    private var scrollListener: RecyclerView.OnScrollListener? = null


    fun setRecyclerView(recyclerView: RecyclerView) {

        this.recyclerView = recyclerView

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView!!.childCount
                totalItemCount = linearLayoutManager!!.itemCount
                lasttVisibleItem = linearLayoutManager!!.findLastVisibleItemPosition()
                if (!isMoreLoading && totalItemCount <= lasttVisibleItem + visibleThreshold) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener!!.onLoadMore()
                    }
                    isMoreLoading = true
                }
            }
        }

        recyclerView.addOnScrollListener(scrollListener)
    }

    fun removeScrollListener() {
        recyclerView!!.removeOnScrollListener(scrollListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
            return MainViewholder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loadmore, parent, false)
            return LoadmoreViewholder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MainViewholder)?.bind(items[position])

        (holder as? LoadmoreViewholder)?.bind()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items!![position] != null) VIEW_ITEM else VIEW_LOADMORE
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setLoadmoreProgress(isProgress: Boolean) {
        if (isProgress) {
            items.add(items.size, NotificationResponseMdl())
            isMoreLoading = true
            notifyDataSetChanged()
        } else {
            isMoreLoading = false
            if (items.size > 0) {
                items.removeAt(items.size - 1)
                notifyDataSetChanged()
            }
        }
    }

    inner class MainViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        var imgUnread = itemView.findViewById<ImageView>(R.id.imgUnread)


        fun bind(item: NotificationResponseMdl) {
            tvTitle.text = item.body
            tvDate.text = DateHelper.convertDateFromApi(item.createdAt!!, DateHelper.DATE_OUTPUT_v1)
            if (item.read==0){
                imgUnread.visibility = View.VISIBLE
            }else{
                imgUnread.visibility = View.GONE
            }
        }
    }

    inner class LoadmoreViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progressbar)
        }

        fun bind() {
            if (isMoreLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}