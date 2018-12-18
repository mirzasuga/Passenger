package com.stickearn.stickpass.view.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log.wtf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.utils.inflate
import com.stickearn.stickpass.view.main.MainActivity
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.*
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.login.LoginActivity
import com.stickearn.stickpass.view.main.MenuAdapter
import com.stickearn.stickpass.view.point.PointActivity
import com.stickearn.stickpass.view.scan.ScanActivity
import com.stickearn.stickpass.view.survey.SurveyActivity
import com.stickearn.stickpass.view.webview.WebviewActivity
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.startActivity


/**
 * Created by oohyugi on 1/24/18.
 */
class HomeFragment : BaseFragment(), HomeView {
    override fun onAuthSuccess(data: LoginResponseMdl) {
        PrefHelper.saveAuth(this.context!!,data)
    }

    override fun showLoadingPoint() {
        progressPoint.visibility = View.VISIBLE
        tvMyPoint.visibility = View.GONE
    }

    override fun stopLoadingPoint() {
        progressPoint.visibility = View.GONE
        tvMyPoint.visibility = View.VISIBLE
    }

    override fun errorLoadingPoint(errorMessage: String) {
        wtf("data point",errorMessage)
        if (isAdded){
            this.activity!!.runOnUiThread {
                if (errorMessage == "The item is null"){
                    tvMyPoint.text = "0 pts"
                }else{
                    tvMyPoint.text = getString(R.string.retry)
                    tvMyPoint.setTextColor(ContextCompat.getColor(this.context!!,R.color.colorRed))
                    tvMyPoint.isClickable = true
                    tvMyPoint.setOnClickListener {
                        callApiPoint()
                    }
                }
            }
        }
    }



    override fun onSuccessLoadMyPoint(result: PointResponseMdl) {
        modelPoint = result

        initDataPoint(result)
    }



    override fun showLoading() {
    }

    override fun errorLoading(errorMessage: String?) {

    }

    override fun stopLoading() {

    }

    override fun displayListBanner(t: List<BannerMdl>) {
        initBanner(t)
    }

    override fun displayMenuData(t: List<MenuMdl>) {
//        initMenu(t)
    }

    fun newsInstance(): HomeFragment {
        val bundle = Bundle()
        val fragment = HomeFragment()

        fragment.arguments = bundle

        return fragment
    }

    lateinit var mPresenter: HomePresenter
    private lateinit var fragmentAdapter: BannerPagerAdapter
    lateinit var progressPoint:ProgressBar
    lateinit var tvMyPoint:TextView
    var modelPoint: PointResponseMdl= PointResponseMdl()
    lateinit var modelUser: ProfileResponseMdl



//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context is Activity) {
//            this.mContext = context as FragmentActivity
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.home_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = HomePresenter(this)

        mPresenter.bannerLoadData()
        mPresenter.menuLoadData()
        progressPoint = view.findViewById(R.id.progressPoint)
        tvMyPoint = view.findViewById(R.id.tvMyPoint)
//        initDataPoint()

        wtf("data User", Gson().toJson(PrefHelper.getRemember(mContext)))
        if (PrefHelper.getAuth(activity!!) != null) {

            tvAccount.text = PrefHelper.getName(mContext)!!.capitalize()
            imgPoint.setImageResource(R.drawable.ic_coins)
            callApiPoint()



        } else {
            tvAccount.text = context!!.resources.getString(R.string.login_or_register)
        }

        ll_account.setOnClickListener({

            if (PrefHelper.getAuth(mContext) != null) {
                val activity = mContext as MainActivity?
                activity!!.gotoAccount()
            } else {
                LoginActivity().startThisActivity(mContext)
//                activity!!.finish()
            }

        })

        ll_point.setOnClickListener {
            if (PrefHelper.getAuth(mContext) != null) {
                mContext.startActivity<PointActivity>()
            } else {
                LoginActivity().startThisActivity(mContext)
//                activity!!.finish()
            }

        }


        initCLickButton()

    }

    private fun initCLickButton() {
        btnFaq.setOnClickListener {
            WebviewActivity().startThisActivity(mContext,"FAQ",4)
        }
        btnPrivacy.setOnClickListener {
            WebviewActivity().startThisActivity(mContext,"Privacy Policy",1)
        }
        btnSurvey.setOnClickListener {
            if (PrefHelper.getAuth(mContext) != null) {
                SurveyActivity().startThisActivity(mContext)
            } else {
                LoginActivity().startThisActivity(mContext)
//                activity!!.finish()
            }
        }
        btnMart.setOnClickListener {
            if (PrefHelper.getAuth(mContext)!=null){
                ScanActivity().startThisActivity(mContext)
            }else{
                LoginActivity().startThisActivity(mContext)
            }
        }
    }

    private fun callApiPoint() {
        mPresenter.getMyPoint(Utils.getAuth(mContext), Utils.getToken(mContext))
    }

    private fun initDataPoint(result: PointResponseMdl) {
        tvMyPoint.setTextColor(ContextCompat.getColor(mContext,R.color.colorText))
        tvMyPoint.isClickable = false
        tvMyPoint.visibility = View.VISIBLE
        tvMyPoint.text = "${result.balance} pts"

    }

    private fun initBanner(result: List<BannerMdl>?) {
        fragmentAdapter = BannerPagerAdapter(childFragmentManager, result!!)
        bannerViewPager.adapter = fragmentAdapter
        mIndicator.setViewPager(bannerViewPager)
        slideBanner(bannerDaos = result)
    }

    var currentPage: Int = 0

    private fun slideBanner(bannerDaos: List<BannerMdl>) {
        val handler = Handler()
        handler.postDelayed({

            if (currentPage === bannerDaos.size) {
                currentPage = 0
            } else
                if (bannerViewPager != null) {

                    bannerViewPager.setCurrentItem(currentPage++, true)
                }
            slideBanner(bannerDaos)
        }, 3000)
    }
}