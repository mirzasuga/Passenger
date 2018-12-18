package com.stickearn.stickpass.view.history


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.wtf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.HistoryMdl
import com.stickearn.stickpass.model.PointHistoryResponseMdl
import com.stickearn.stickpass.utils.EndlessRecyclerViewScrollListener
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.utils.inflate
import com.stickearn.stickpass.view.login.LoginActivity
import kotlinx.android.synthetic.main.history_fragment.*
import kotlinx.android.synthetic.main.no_data.*


class HistoryFragment : BaseFragment(),HistoryView {
    override fun showLoading() {
        if (!isSwipeRefresh) {
            progressView.visibility  = View.VISIBLE
        }

    }

    override fun errorLoading(errorMessage: String?) {
        Toast.makeText(mContext,errorMessage,Toast.LENGTH_LONG).show()
    }

    override fun stopLoading() {
        progressView.visibility  = View.GONE
        if (swipeRefresh != null) {
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onLoadedDataSuccess(t: List<PointHistoryResponseMdl>) {
        if (t.size>0){
            listHistory.addAll(t)
            adapter.notifyDataSetChanged()
        }else{
            layoutNoData.visibility = View.VISIBLE
            tvDescNoCampaign.text = "Data empty"
        }



    }


    lateinit var mPresenter: HistoryFragmentPresenter
    var listIncom:MutableList<HistoryMdl> = mutableListOf()
    var listOutcom:MutableList<HistoryMdl> = mutableListOf()
    var listHistory:MutableList<PointHistoryResponseMdl> = mutableListOf()
    lateinit var adapter: HistoryAdapter
    lateinit var progressView: LinearLayout
    var isSwipeRefresh = false
    var currentPage: Int = 1
    var maxPage = 0
    lateinit var layoutNoData :LinearLayout
    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.history_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.title="History"
        mPresenter = HistoryFragmentPresenter(this)
        progressView = view.findViewById(R.id.progress_dialog)
        layoutNoData = view.findViewById(R.id.ly_noData)
        initData()


        if(PrefHelper.getAuth(mContext)!=null){
            mPresenter.loadHistory(
                    Utils.getAuth(mContext),
                    Utils.getToken(mContext),
                    Utils.getUuidUser(mContext),

            1)
        }else{
//            ly_noData.visibility = View.VISIBLE
//            tvDescNoCampaign.text = "Please Login"
            LoginActivity().startThisActivity(mContext)
            activity!!.finish()

        }

        swipeRefresh.setOnRefreshListener {
            isSwipeRefresh = true
            listHistory.clear()
            currentPage = 1
            adapter.notifyDataSetChanged()
            scrollListener.resetState()
            mPresenter.loadHistory(Utils.getAuth(this.context!!), Utils.getToken(this.context!!), Utils.getUuidUser(mContext),1)

        }



    }



    private fun initData() {

        val linearLayoutManager= LinearLayoutManager(mContext)
        rvHistory.layoutManager = linearLayoutManager
        adapter = HistoryAdapter(activity!!, listHistory)
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {

                currentPage++


                if (currentPage<=maxPage) {
//                    adapter.isMoreLoading = true
                    isSwipeRefresh=true
                    mPresenter.loadHistory(Utils.getAuth(context!!), Utils.getToken(context!!),Utils.getUuidUser(mContext), currentPage)

                } else {
                    isSwipeRefresh=false

                }


            }
        }
        rvHistory.adapter = adapter
        rvHistory.addOnScrollListener(scrollListener)
    }


    fun newsInstance() : HistoryFragment {
        val bundle=Bundle()
        val fragment= HistoryFragment()

        fragment.arguments=bundle

        return fragment
    }
}
