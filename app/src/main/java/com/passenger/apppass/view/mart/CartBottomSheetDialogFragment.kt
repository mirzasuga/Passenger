package com.stickearn.stickpass.view.mart

import android.annotation.SuppressLint
import android.app.Dialog
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.view.View
import com.stickearn.stickpass.R

import kotlinx.android.synthetic.main.cart_bottom_sheet_dialog.*


/**
 * Created by oohyugi on 1/24/18.
 */
class CartBottomSheetDialogFragment: BottomSheetDialogFragment() {

    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null
    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
//            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//
//            }
           // mBottomSheetBehavior.s


        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {}
    }



    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
      super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.cart_bottom_sheet_dialog, null)
        dialog.setContentView(contentView)

        val params = (contentView.getParent() as View).getLayoutParams() as CoordinatorLayout.LayoutParams
      mBottomSheetBehavior = BottomSheetBehavior.from(contentView.parent as View)
        val behavior = params.behavior
        if (mBottomSheetBehavior != null) {
            (mBottomSheetBehavior as BottomSheetBehavior<View>).setBottomSheetCallback(mBottomSheetBehaviorCallback);
            (mBottomSheetBehavior as BottomSheetBehavior<View>).setPeekHeight(180)
            contentView.requestLayout()
        }
//        if (behavior != null && behavior is BottomSheetBehavior<*>) {
//            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
//        }
    }
}