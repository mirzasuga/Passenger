package com.stickearn.stickpass.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.stickearn.stickpass.R
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by oohyugi on 1/17/18.
 */
open class BaseFragment: Fragment(){

   protected var compositeDisposable: CompositeDisposable?=null
    override fun onDestroy() {
        DisposableManager().dispose()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
//        DisposableManager().dispose()
    }

    lateinit var mContext: FragmentActivity
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity) {
            this.mContext = context as FragmentActivity
        }
    }


}