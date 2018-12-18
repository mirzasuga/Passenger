package com.stickearn.stickpass.view.voucher_detail

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.util.Log.wtf
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.ebanx.swipebtn.OnActiveListener
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.model.*
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformation
import com.stickearn.stickpass.view.point.PointActivity
import kotlinx.android.synthetic.main.voucher_detail_activity.*
import com.ebanx.swipebtn.OnStateChangeListener
import com.ebanx.swipebtn.SwipeButton
import kotlinx.android.synthetic.main.voucher_code.*
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.widget.ImageView
import com.stickearn.stickpass.R.id.tvName
import android.util.Pair as UtilPair




class VoucherDetailActivity : BaseActivity(), VoucherDetailView {


    override fun onRedeemSuccess(result: BaseMdl<PurchasedResponseMdl>) {
        btnRedeem.visibility =View.GONE
        swipeButton.visibility = View.GONE
        lyVoucherCode.visibility=View.VISIBLE
        tvVoucherCode.text = mItem.codeVoucher
        lyVoucherCode.setOnClickListener {
            Utils.setClipboard(this,mItem.codeVoucher)
        }


    }

    override fun showLoading() {
        progressBar.visibility=View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
    }

    override fun stopLoading() {
        progressBar.visibility=View.GONE
    }

    override fun showLoadingPurchased() {
        progressBar.visibility=View.VISIBLE
    }

    override fun stopLoadingPurchased() {
        progressBar.visibility=View.GONE
    }

    override fun onPurchasedSuccess(result: BaseMdl<PurchasedResponseMdl>) {
        showDialogPurchasedSuccess(result)
    }



    lateinit var mItem:VoucherMdl
    lateinit var mPresenter: VoucherDetailPresenter
    lateinit var progressBar:LinearLayout
    lateinit var swipeButton: SwipeButton
    lateinit var imgDetail:ImageView
    var fromView=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voucher_detail_activity)
        mItem = intent.getSerializableExtra(TAG_ITEM) as VoucherMdl
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        progressBar = findViewById(R.id.progress_dialog)
        imgDetail = findViewById(R.id.img_detail)
        swipeButton = findViewById<View>(R.id.swipe_btn) as SwipeButton
        if (intent.getIntExtra(TAG_FROM,0)!=0){
            fromView = intent.getIntExtra(TAG_FROM,0)
            if (fromView==1){
                initDetailAllVoucher()
            }else if (fromView==2){
                initDetailMyVoucher()
            }

        }
        tv_voucher_title.text = mItem.namaVoucher
        tv_point_redeem.text = mItem.amount.toString()
        tvDesc.text = mItem.description
        tvValid.text ="${getString(R.string.valid_until)} ${DateHelper.convertDateFromApi(mItem.validtodate, DateHelper.DATE_OUTPUT_v1)}"
        imgDetail.loadImageWithPicassoTransformation(mItem.image.toString())
        mPresenter = VoucherDetailPresenter(this)
        btnRedeem.setOnClickListener {
            showDilaogPurchasedVoucher(mItem)
        }

    }

    private fun initDetailMyVoucher() {
        btnRedeem.visibility = View.GONE
        swipe_btn.visibility = View.VISIBLE

        swipeButton.setOnActiveListener(OnActiveListener {

            showDilaogRedeemVoucher(mItem)
//            Toast.makeText(this,"Voucher active",Toast.LENGTH_LONG).show()
        })

    }

    private fun initDetailAllVoucher() {
        btnRedeem.visibility = View.VISIBLE
        swipeButton.visibility = View.GONE




    }

    private fun showDilaogRedeemVoucher(item: VoucherMdl) {
        swipeButton.setHasActivationState(false)
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.get_voucher))
        dialog.setMessage("${getString(R.string.do_you_want_to_use)} ${item.namaVoucher} ${getString(R.string.now)} ? ")
        dialog.setNegativeButton("Cancel") { dialog, which -> dialog!!.dismiss()
            swipeButton.setHasActivationState(false)
//        swipeButton.isActivated=false
        }
        dialog.setPositiveButton("Yes") { dialog, which ->
            val purchasedData = PurchasedRequestMdl("",item.amount.toString(),"redeem",item.codeVoucher,"voucher")
            val data  = PayloadRequestBaseMdl(purchasedData)
            mPresenter.redeemVoucher(Utils.getAuth(this),
                    Utils.getToken(this),data)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDilaogPurchasedVoucher(item: VoucherMdl) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.buy_voucher))
        dialog.setMessage("${getString(R.string.are_you_sure_want_to_buy)} ${item.namaVoucher} voucher for ${item.amount} points ?")
        dialog.setNegativeButton(getString(R.string.cancel)) { dialog, which -> dialog!!.dismiss() }
        dialog.setPositiveButton(getString(R.string.buy)) { dialog, which ->
            val purchasedData = PurchasedRequestMdl("b85ceccc-0523-45c3-8943-0f5c3adc4c68",item.amount.toString(),"purchased",item.id.toString(),"voucher")
            val data  = PayloadRequestBaseMdl(purchasedData)
            mPresenter.purchasedVoucher(Utils.getAuth(this),
                    Utils.getToken(this),data)
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun showDialogPurchasedSuccess(result: BaseMdl<PurchasedResponseMdl>) {

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.purchase_successfull))
        dialog.setMessage(getString(R.string.message_success_purchase))
        dialog.setNegativeButton("Later") { dialog, which -> dialog!!.dismiss() }
        dialog.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            PointActivity().startThisActivity(this,true)
            this.finish()
        }
        dialog.show()
    }
    val TAG_ITEM ="item"
    val TAG_FROM ="from"


    /**
     * from view 1:all voucher, 2:my voucher
     */
    fun startThisActivity(context: Activity, item: VoucherMdl,fromView:Int) {
        val intent = Intent(context, VoucherDetailActivity::class.java)
        intent.putExtra(TAG_ITEM,item)
        intent.putExtra(TAG_FROM,fromView)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
        }else{
            context.startActivity(intent)
        }
    }
}
