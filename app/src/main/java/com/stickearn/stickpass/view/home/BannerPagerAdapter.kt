package com.stickearn.stickpass.view.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.stickearn.stickpass.model.BannerMdl

/**
 * Created by macos-vanya on 17/01/18.
 */

class BannerPagerAdapter (fm: FragmentManager?,var list: List<BannerMdl>) : FragmentStatePagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        if (list.isNotEmpty()){
            return BannerFragment().newsInstance(list[position])
        }

        return BannerFragment().newsInstance(list[position])

    }

    override fun getCount(): Int {
        return list.size

    }

}