package com.stickearn.stickpass.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.model.BannerMdl
import com.stickearn.stickpass.utils.inflate
import com.stickearn.stickpass.utils.loadImageWithPicasso
import com.stickearn.stickpass.view.mart_detail.MartDetailActivity
import kotlinx.android.synthetic.main.banner_fragment.*

/**
 * Created by macos-vanya on 17/01/18.
 */
class BannerFragment : BaseFragment() {


    var TAG_DATA:String="data"
    fun newsInstance( bannerMdl: BannerMdl): BannerFragment {
        val args=Bundle()
        args.putParcelable(TAG_DATA,bannerMdl)
        val fragment = BannerFragment()
        fragment.arguments=args
        return fragment

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.banner_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mData : BannerMdl = arguments!!.getParcelable(TAG_DATA)
        bannerImg.loadImageWithPicasso(mData.imgUrl!!)
        bannerImg.setOnClickListener {
//            MartDetailActivity().startThisActivity(activity!!)
        }
    }

}