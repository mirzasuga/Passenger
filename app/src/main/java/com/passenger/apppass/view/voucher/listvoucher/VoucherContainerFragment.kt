package com.stickearn.stickpass.view.voucher.listvoucher


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar

import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.*
import com.stickearn.stickpass.utils.EndlessRecyclerViewScrollListener
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.point.PointActivity
import kotlinx.android.synthetic.main.point_container.*


/**
 * A simple [Fragment] subclass.
 */
class VoucherContainerFragment : BaseFragment(), VoucherView, VoucherAdapter.OnClickItem {
    override fun showLoadingPurchased() {
        mProgressPurchased.visibility=View.VISIBLE
    }

    override fun stopLoadingPurchased() {
        mProgressPurchased.visibility = View.GONE
    }

    override fun onPurchasedSuccess(result: BaseMdl<PurchasedResponseMdl>) {
        showDialogPurchasedSuccess(result)
    }

    lateinit var mProgressPurchased: ProgressBar
    override fun onClickPurchased(item: VoucherMdl, progressBar: ProgressBar) {
        if (mPoint <item.amount){
            showDilaogErrorr()
        }else{
            mProgressPurchased = progressBar
            showDilaogPurchasedVoucher(item)
        }
    }

    private fun showDilaogPurchasedVoucher(item: VoucherMdl) {
        val dialog = AlertDialog.Builder(mContext)
        dialog.setTitle("Buy Voucher")
        dialog.setMessage("Are you sure want to buy ${item.namaVoucher} voucher for ${item.amount} points ?")
        dialog.setNegativeButton("Cancel") { dialog, which -> dialog!!.dismiss() }
        dialog.setPositiveButton("Buy") { dialog, which ->
            val purchasedData = PurchasedRequestMdl("b85ceccc-0523-45c3-8943-0f5c3adc4c68",item.amount.toString(),"purchased",item.id.toString(),"voucher")
            val data  =PayloadRequestBaseMdl(purchasedData)
            mPresenter.purchasedVoucher(Utils.getAuth(mContext),
                    Utils.getToken(mContext),data)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDilaogErrorr() {
        val dialog = AlertDialog.Builder(mContext)
        dialog.setTitle("Sorry")
        dialog.setMessage("Your point is not enough")
        dialog.setNegativeButton("Dismiss") { dialog, which -> dialog!!.dismiss() }
        dialog.show()
    }

    private fun showDialogPurchasedSuccess(result: BaseMdl<PurchasedResponseMdl>) {

        val dialog = AlertDialog.Builder(mContext)
        dialog.setTitle(getString(R.string.purchase_successfull))
        dialog.setMessage(getString(R.string.message_success_purchase))
        dialog.setNegativeButton("Later") { dialog, which -> dialog!!.dismiss() }
        dialog.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            PointActivity().startThisActivity(mContext,true)
            mContext.finish()
        }
        dialog.show()
    }
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

    override fun onGetVoucherSuccess(t: BaseMdl<List<VoucherMdl>>) {
//        maxPageMap.put(t.meta.currentPage, t.meta.maxPage)


        if (t.data.size > 0) {
            maxPage=t.meta.maxPage
            listRedeem.addAll(t.data)
            adapter.notifyItemRangeInserted(listRedeem.size - 1, t.data.size)

        }
    }

    fun newsInstance( point: Int): VoucherContainerFragment {
        val args= Bundle()
        args.putInt(TAG_POINT,point)
        val fragment = VoucherContainerFragment()
        fragment.arguments=args
        return fragment
    }

    lateinit var mPresenter: VoucherPresenter
    lateinit var adapter: VoucherAdapter
    lateinit var mProgressBar:LinearLayout
    var listRedeem:MutableList<VoucherMdl> = mutableListOf()
    var currentPage: Int = 1
    var maxPage = 0
    var maxPageMap: HashMap<Int, Int> = HashMap<Int, Int>()
    val TAG_POINT= "point"
    var mPoint:Int=0

    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.point_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mProgressBar = view.findViewById(R.id.progress_dialog)

        mPoint = arguments!!.getInt(TAG_POINT,0)
        initDataList()

        mPresenter = VoucherPresenter(this)
        if (PrefHelper.getAuth(mContext)!=null){
            mPresenter.getVoucher(Utils.getAuth(mContext), Utils.getToken(mContext), currentPage)
        }
    }

    private fun initDataList() {
        val linearLayoutManager= LinearLayoutManager(mContext)
        rv_point.layoutManager = linearLayoutManager
        rv_point.setHasFixedSize(true)
        adapter = VoucherAdapter(activity!!, listRedeem, this)
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                currentPage++

                if (maxPage >= currentPage) {
                    adapter.isMoreLoading = true
                    mPresenter.getVoucher(Utils.getAuth(mContext), Utils.getToken(mContext),currentPage)
                }
                else {
                    adapter.isMoreLoading = false
                    adapter.notifyDataSetChanged()

                }
            }
        }
        rv_point.adapter = adapter

        rv_point.addOnScrollListener(scrollListener)
    }
}
