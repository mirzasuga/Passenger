package com.stickearn.stickpass.view.notification

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.model.NotificationResponseMdl
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.utils.inflate
import kotlinx.android.synthetic.main.notification_fragment.*
import com.stickearn.stickpass.utils.EndlessRecyclerViewScrollListener
import android.support.v7.widget.RecyclerView
import android.util.Log.wtf
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.view.login.LoginActivity
import kotlinx.android.synthetic.main.no_data.*







/**
 * Created by oohyugi on 2/26/18.
 */
class NotificationFragment : BaseFragment(), NotificationView {

    override fun showLoading() {

        if (!isSwipeRefresh && !adapter.isMoreLoading) {
            progressDialog.visibility = View.VISIBLE
        }


    }

    override fun errorLoading(errorMessage: String?) {
        Utils.showSnackBar(lyNotif, errorMessage!!, false)
    }

    override fun stopLoading() {

        progressDialog.visibility = View.GONE
        if (swipeRefresh != null) {
            swipeRefresh.isRefreshing = false
        }


    }

    override fun displayNotif(t: BaseMdl<List<NotificationResponseMdl>>) {
        maxPageMap.put(t.meta.currentPage, t.meta.maxPage)
        maxPage=t.meta.maxPage

        if (t.data.size > 0) {
            if (currentPage==1){
                listNotif.clear()
                listNotif.addAll(t.data)
            }else{
                listNotif.addAll(t.data)
            }


            lyNoData.visibility = View.GONE
            adapter.notifyItemRangeInserted(listNotif.size - 1, t.data.size)

        } else if (t.meta.currentPage == 1 && t.data.size < 1) {
            lyNoData.visibility = View.VISIBLE
        }


    }

    fun newsInstance(): NotificationFragment {
        val bundle = Bundle()
        val fragment = NotificationFragment()

        fragment.arguments = bundle

        return fragment
    }

    var listNotif: MutableList<NotificationResponseMdl> = mutableListOf()
    lateinit var adapter: NotificationAdapter
    lateinit var mPresenter: NotificationPresenter
    lateinit var lyNoData: LinearLayout
    lateinit var lyNotif: RelativeLayout
    lateinit var progressDialog: LinearLayout
    var isSwipeRefresh = false
    var currentPage: Int = 1
    var maxPage = 0
    var maxPageMap: HashMap<Int, Int> = HashMap<Int, Int>()
    lateinit var scrollListener: EndlessRecyclerViewScrollListener
    lateinit var callBackListener: CallBackListener
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (mContext is CallBackListener)
            callBackListener = (mContext as CallBackListener?)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.notification_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.title = "Notification"
        lyNoData = view.findViewById(R.id.ly_noData)
        progressDialog = view.findViewById(R.id.progress_dialog)
        lyNotif = view.findViewById(R.id.ly_notif)
        mPresenter = NotificationPresenter(this)
        if (PrefHelper.getAuth(mContext)!=null){
            mPresenter.loadNotif(Utils.getAuth(this.context!!), Utils.getToken(this.context!!), 1, 1)
        }else{
//            lyNoData.visibility = View.VISIBLE
//            tvDescNoCampaign.text="Please login"
            LoginActivity().startThisActivity(mContext)
            activity!!.finish()
        }
//        mPresenter.loadNotif(Utils.getAuth(this.context!!), Utils.getToken(this.context!!), 1, 1)
        swipeRefresh.setOnRefreshListener {
            isSwipeRefresh = true
            listNotif.clear()
            currentPage = 1
            adapter.notifyDataSetChanged()
            scrollListener.resetState()
            mPresenter.loadNotif(Utils.getAuth(this.context!!), Utils.getToken(this.context!!), 1, 1)

        }

    }

    private fun initData() {
        val linearLayoutManager= LinearLayoutManager(context)
        adapter = NotificationAdapter(activity!!, listNotif)
        rvNotif.layoutManager = linearLayoutManager
        rvNotif.setHasFixedSize(true)
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                currentPage++
                if (currentPage<=maxPage) {
//                    adapter.isMoreLoading = true
                    isSwipeRefresh=true
                    swipeRefresh.isRefreshing=isSwipeRefresh
                    mPresenter.loadNotif(Utils.getAuth(context!!), Utils.getToken(context!!), currentPage, 1)

                }
//                else{
//                    if (adapter.isMoreLoading){
//                        adapter.isMoreLoading = false
//                        adapter.notifyDataSetChanged()
//                    }
//                }
//                else {
//                    adapter.isMoreLoading = false
//                    adapter.notifyDataSetChanged()
////                    isSwipeRefresh=false
//
//                }


            }
        }
        rvNotif.adapter = adapter

        rvNotif.addOnScrollListener(scrollListener)
    }

    override fun onResume() {
        super.onResume()
//
//        adapter.notifyDataSetChanged()
//        currentPage=1


//        listNotif.clear()
    }

    interface CallBackListener {
        fun onCallBack()
    }
}