package com.stickearn.stickpass.view.onboarding

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.model.OnBoardingMdl
import com.stickearn.stickpass.utils.inflate
import com.stickearn.stickpass.utils.loadImageWithPicasso
import com.stickearn.stickpass.view.mart_detail.MartDetailActivity
import kotlinx.android.synthetic.main.onboarding_fragment.*

/**
 * Created by oohyugi on 1/17/18.
 */
class OnboardingFragment: BaseFragment() {

    var TAG_DATA:String="data"
    fun newsInstance( onBoardingMdl: OnBoardingMdl):OnboardingFragment{
        val args=Bundle()
        args.putParcelable(TAG_DATA,onBoardingMdl)
        val fragment = OnboardingFragment()
        fragment.arguments=args
        return fragment

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.onboarding_fragment)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mData : OnBoardingMdl = arguments!!.getParcelable(TAG_DATA)
        obImg.loadImageWithPicasso(mData.imgUrl!!)

    }

}


