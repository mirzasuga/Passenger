package com.stickearn.stickpass.view.voucher.myvoucher

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.VoucherMdl
import com.stickearn.stickpass.utils.EndlessRecyclerViewScrollListener
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.voucher.listvoucher.VoucherAdapter
import kotlinx.android.synthetic.main.no_data.*
import kotlinx.android.synthetic.main.point_container.*

/**
 * Created by macos-vanya on 07/02/18.
 */
class MyVoucherContainerFragment : BaseFragment(), MyVoucherView {
    override fun showLoading() {
        if (!adapter.isMoreLoading){
            mProgressBar.visibility= View.VISIBLE
        }

    }

    override fun errorLoading(errorMessage: String?) {
    }

    override fun stopLoading() {
        mProgressBar.visibility= View.GONE
    }

    override fun onGetVoucherSuccess(result: BaseMdl<List<VoucherMdl>>) {
        if (result.data.size>0){
            listVoucher.addAll(result.data)
            maxPage=result.meta.maxPage
            adapter.notifyDataSetChanged()
        }else{
            ly_noData.visibility = View.VISIBLE
            tvDescNoCampaign.text = "Sorry, your voucher is empty"
        }


    }

    var TAG_DATA:String="data"
    fun newsInstance( history: String): MyVoucherContainerFragment {
        val args= Bundle()
        args.putString(TAG_DATA,history)
        val fragment = MyVoucherContainerFragment()
        fragment.arguments=args
        return fragment
    }

    lateinit var mPresenter: MyVoucherPresenter
    lateinit var mProgressBar: LinearLayout
    lateinit var adapter: NewVoucherAdapter
    lateinit var scrollListener: EndlessRecyclerViewScrollListener
    var currentPage: Int = 1
    var maxPage = 0
    var listVoucher:MutableList<VoucherMdl> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.point_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mProgressBar = view.findViewById(R.id.progress_dialog)

        initDataList()

        mPresenter = MyVoucherPresenter(this)
        mPresenter.getVoucher(Utils.getAuth(this.activity!!),
                Utils.getToken(this.activity!!),1)
    }

    private fun initDataList() {
        val linearLayoutManager= LinearLayoutManager(mContext)
        rv_point.layoutManager = linearLayoutManager
        rv_point.setHasFixedSize(true)
        adapter = NewVoucherAdapter(activity!!, listVoucher)
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                currentPage++

                if (maxPage >= currentPage && listVoucher.size>9) {
                    adapter.isMoreLoading = true
                    mPresenter.getVoucher(Utils.getAuth(mContext),
                            Utils.getToken(mContext),currentPage)


                }
                else  {
                    adapter.isMoreLoading = false
                    adapter.notifyDataSetChanged()

                }


            }
        }
        rv_point.adapter = adapter
//        adapter.notifyDataSetChanged()
       rv_point.addOnScrollListener(scrollListener)
    }


}