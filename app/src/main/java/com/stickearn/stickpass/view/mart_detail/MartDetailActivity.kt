package com.stickearn.stickpass.view.mart_detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.CheckoutResponseMdl
import com.stickearn.stickpass.model.MartResponseMdl
import com.stickearn.stickpass.utils.*
import com.stickearn.stickpass.view.order_complete.OrderCompleteActivity
import com.stickearn.stickpass.view.webview.WebviewActivity
import kotlinx.android.synthetic.main.mart_detail_activity.*

class MartDetailActivity : BaseActivity(),MartDetailView {
    override fun showLoading() {
        if (mProgressDialog.visibility==View.GONE){
            mProgressDialog.visibility = View.VISIBLE
        }
    }

    override fun errorLoading(errorMessage: String?) {
        Utils.showSnackBar(martDetailView, errorMessage!!,false)

    }

    override fun stopLoading() {
        if (mProgressDialog.visibility==View.VISIBLE){
            mProgressDialog.visibility = View.GONE
        }
    }

    override fun onSuccessLoadDetailMart(result: MartResponseMdl) {
        mData = result
        initDetailMart(result)
    }

    private fun initDetailMart(result: MartResponseMdl) {

        tvTitle.text = result.productName
        tvPrice.text = "${StringHelper.indonesiaFormat(result.priceCurrency.toDouble())} / ${result.pricePoint} point"
        tvDesc.text = Html.fromHtml(result.productDescription)
        img_detail.loadImageWithPicassoResize(result.mainImage.data.imageUrl)
//        img_detail.loadImageWithPicassoResize(result.mainImage.data.imageUrl)

    }

    val TAG_PRODUCT_ID = "productId"
    val TAG_BOX_ID = "boxId"
    fun startThisActivity(context:Context,productId:String,boxId:String){
        val intent =Intent(context, MartDetailActivity::class.java)

        intent.putExtra(TAG_PRODUCT_ID,productId)
        intent.putExtra(TAG_BOX_ID,boxId)
        context.startActivity(intent)

    }
    fun startThisActivity(context: Context) {
        val intent = Intent(context, MartDetailActivity::class.java)
        context.startActivity(intent)
    }
    lateinit var mData: MartResponseMdl
    lateinit var mProductId:String
    lateinit var mBoxId:String
    lateinit var mProgressDialog:LinearLayout
    lateinit var mPresenter:MartDetailPresenter
    lateinit var martDetailView: CoordinatorLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mart_detail_activity)
        mProgressDialog = findViewById(R.id.progress_dialog)
        martDetailView = findViewById(R.id.product_detail_activity)
        mProductId= intent.getStringExtra(TAG_PRODUCT_ID)
        mBoxId= intent.getStringExtra(TAG_BOX_ID)
        mPresenter = MartDetailPresenter(this)
        mPresenter.getMartDetail(mBoxId,mProductId,Utils.getAuth(this),Utils.getToken(this))
        supportActionBar!!.title = "Mart Detail"

    }



}
