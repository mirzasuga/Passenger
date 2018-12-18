package com.stickearn.stickpass.view.onboarding

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.stickearn.stickpass.model.OnBoardingMdl

/**
 * Created by oohyugi on 1/17/18.
 */
class OnboardingFragmentPagerAdapter(fm: FragmentManager?,var list: List<OnBoardingMdl>) : FragmentStatePagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        if (list.isNotEmpty()){
            return OnboardingFragment().newsInstance(list[position])
        }

        return OnboardingFragment().newsInstance(list[position])

    }

    override fun getCount(): Int {
        return list.size

    }

}