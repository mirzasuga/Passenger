package com.stickearn.stickpass.view.order_complete

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stickearn.stickpass.R
import com.stickearn.stickpass.view.main.MainActivity
import kotlinx.android.synthetic.main.order_complete_activity.*
import org.jetbrains.anko.startActivity
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.wtf
import android.view.View
import android.widget.LinearLayout
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.CheckoutResponseMdl
import com.stickearn.stickpass.model.DataOrderDetailMdl
import com.stickearn.stickpass.model.OrderDetailMdl
import com.stickearn.stickpass.utils.Utils

class OrderCompleteActivity : AppCompatActivity(), OrderCompleteView {
    lateinit var mData: BaseMdl<OrderDetailMdl>
    lateinit var mProgressDialog: LinearLayout
    lateinit var orderDetailView: CoordinatorLayout
    var isFirstLoadData:Boolean = false
    var orderDetailMdlList = mutableListOf<DataOrderDetailMdl>()
    lateinit var mAdapter: OrderDetailAdapter

    override fun onSuccessLoadDetailMart(result: BaseMdl<OrderDetailMdl>) {
        mData = result
        orderDetailMdlList.addAll(result.data.items.data)
        mAdapter.notifyDataSetChanged()

    }

    override fun showLoading() {
        if (mProgressDialog.visibility== View.GONE){
            mProgressDialog.visibility = View.VISIBLE
        }
    }

    override fun errorLoading(errorMessage: String?) {
        Utils.showSnackBar(orderDetailView, errorMessage!!,false)
    }

    override fun stopLoading() {
        if (mProgressDialog.visibility==View.VISIBLE){
            mProgressDialog.visibility = View.GONE
        }
    }

    var TAG_DATA ="mdata"
    lateinit var mPresenter:OrderCompletePresenter

    fun startThisActivity(context:Context,mData: CheckoutResponseMdl){
        val intent =Intent(context,OrderCompleteActivity::class.java)
        intent.putExtra(TAG_DATA,mData)
        context.startActivity(intent)
    }

    lateinit var mCheckoutData: CheckoutResponseMdl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_complete_activity)
        isFirstLoadData=true
        initOrderDetail()
        btnOrderComplete.setOnClickListener{
            startActivity<MainActivity>()
            finish()
        }

        mProgressDialog = findViewById(R.id.progress_dialog)
        orderDetailView = findViewById(R.id.cl_order_detail)

        mCheckoutData = intent.getParcelableExtra(TAG_DATA)
        wtf("mCheckoutData",mCheckoutData.toString())

        mPresenter = OrderCompletePresenter(this)
        mPresenter.getOrderDetail(mCheckoutData.orderUuid, Utils.getAuth(this),Utils.getToken(this))

        tv_order_number.text = mCheckoutData.purchaseId
        tv_payment_method.text = mCheckoutData.paymentMethod


        if (mCheckoutData.grandTotalPricePoint==0){
            tv_final_ammount.text = StringHelper.indonesiaFormat(mCheckoutData.grandTotalPriceCurrency.toDouble())
        }else{
            tv_final_ammount.text = "${mCheckoutData.grandTotalPricePoint} point"
        }
        startCheckAnimation()
    }

    private fun startCheckAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500)
        animator.addUpdateListener { valueAnimator -> lottieAnimationView.progress = valueAnimator.animatedValue as Float }

        if (lottieAnimationView.progress === 0f) {
            animator.start()
            animator.duration = 3000
        } else {
            lottieAnimationView.progress = 0f
        }
    }

    private fun initOrderDetail() {

        mAdapter = OrderDetailAdapter(this, orderDetailMdlList)
        rv_order_detail.adapter = mAdapter
        rv_order_detail.layoutManager = LinearLayoutManager(this)
        mAdapter.notifyDataSetChanged()
    }



    override fun onBackPressed() {
        startActivity<MainActivity>()
        finish()
    }
}
