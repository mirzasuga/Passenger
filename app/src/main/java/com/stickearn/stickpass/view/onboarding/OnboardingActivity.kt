package com.stickearn.stickpass.view.onboarding

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.OnBoardingMdl
import com.stickearn.stickpass.view.main.MainActivity
import kotlinx.android.synthetic.main.onboarding_activity.*
import org.jetbrains.anko.startActivity

class OnboardingActivity : AppCompatActivity(),OnboardingView {
    override fun onLoadedDataSuccess(t: List<OnBoardingMdl>) {
        initData(t)
    }

    private lateinit var fragmentAdapter:OnboardingFragmentPagerAdapter
    var currentPos: Int=0
    lateinit var mPresenter:OnboardingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_activity)
        mPresenter = OnboardingPresenter(this)
        mPresenter.loadData()



    }






    private fun initData(result: List<OnBoardingMdl>?) {
        fragmentAdapter=OnboardingFragmentPagerAdapter(supportFragmentManager, result!!)
        obViewPager.adapter=fragmentAdapter
        obIndicator.setViewPager(obViewPager)
        obViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentPos=position
                if (position>=1){
                    obBtnSkip.visibility= View.VISIBLE
                }else{
                    obBtnSkip.visibility= View.GONE
                }
            }

            override fun onPageSelected(position: Int) {

            }

        })

        obBtnNext.setOnClickListener({
            if (currentPos!=result.size-1){
                currentPos++
                obViewPager.currentItem = currentPos
            }else{
                PrefHelper.saveFirstInstall(this,false)
                startActivity<MainActivity>()
                finish()
            }

            Log.wtf("curr", currentPos.toString())
        })

        obBtnSkip.setOnClickListener {
            PrefHelper.saveFirstInstall(this,false)
            startActivity<MainActivity>()
            finish()
        }

    }

}
