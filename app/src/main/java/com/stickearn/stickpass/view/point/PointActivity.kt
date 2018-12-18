package com.stickearn.stickpass.view.point


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.PointMdl
import com.stickearn.stickpass.model.PointResponseMdl
import com.stickearn.stickpass.model.VoucherMdl
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.voucher.listvoucher.VoucherContainerFragment
import com.stickearn.stickpass.view.voucher.myvoucher.MyVoucherContainerFragment
import kotlinx.android.synthetic.main.activity_point.*

class PointActivity : BaseActivity(),PointView {


    override fun showLoading() {

    }

    override fun errorLoading(errorMessage: String?) {
    }

    override fun stopLoading() {
    }

    override fun onGetPointSuccess(result: PointResponseMdl) {
        initDataPoint(result)
    }

    @SuppressLint("SetTextI18n")
    private fun initDataPoint(result: PointResponseMdl) {
        mPoint = result.balance
        tvPoint.text = result.balance.toString()+" pts"
//        tvCurrency.text = result.
    }

    override fun displayData(t: List<PointMdl>) {
//        initData(t)
    }

    private lateinit var mPresenter: PointPresenter
    var listMyVoucher:MutableList<VoucherMdl> = mutableListOf()
    var gotoMyVoucher:Boolean=false
    var mPoint=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point)

        setSupportActionBar(toolbar)
        getSupportActionBar()!!.elevation = 0F
        supportActionBar!!.title="Point"
        mPresenter = PointPresenter(this)
        mPresenter.loadData()

        if (intent.getBooleanExtra(TAG_FROM,false)){
            gotoMyVoucher=intent.getBooleanExtra(TAG_FROM,true)
        }
        setupViewPager()

        if (PrefHelper.getAuth(this)!=null){
            mPresenter.getPoint(Utils.getAuth(this), Utils.getToken(this))
        }
    }

    private fun setupViewPager() {
        tabLayoutVoucher.setupWithViewPager(viewPagerVoucher)
        val pagerAdapter= PointPagerAdapter(supportFragmentManager)

        val fragRedeem = VoucherContainerFragment().newsInstance(mPoint)
        val fragMyVoucher = MyVoucherContainerFragment().newsInstance(Gson().toJson(listMyVoucher))
        pagerAdapter.addFragment(fragRedeem,getString(R.string.buy_voucher))
        pagerAdapter.addFragment(fragMyVoucher,getString(R.string.my_voucher))
        viewPagerVoucher.adapter = pagerAdapter
        if (gotoMyVoucher){
            tabLayoutVoucher.setScrollPosition(1,0f,true)
            viewPagerVoucher.currentItem = 1
        }
    }

    val TAG_FROM = "from"
    val TAG_ITEM= "item"
    fun startThisActivity(context: Context,gotoMyVoucher:Boolean) {
        val intent = Intent(context, PointActivity::class.java)
        intent.putExtra(TAG_FROM,gotoMyVoucher)
        context.startActivity(intent)
    }
}
