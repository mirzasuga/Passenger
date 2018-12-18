package com.stickearn.stickpass.utils

import android.content.Context
import android.widget.LinearLayout
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.util.AttributeSet
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.stickearn.stickpass.R


/**
 * Created by oohyugi on 3/16/18.
 */
class CustomProgressView
/**
 * @param context
 * @param attrs
 */
(internal var ctx: Context, attrs: AttributeSet) : LinearLayout(ctx, attrs) {
    internal var ivProgress: ViewGroup
    internal var ivProgressError: ViewGroup
    internal var tvMessage: TextView


    init {

        val v = LayoutInflater.from(ctx).inflate(R.layout.custom_progress,
                null)

        // progress
        ivProgress = v.findViewById(R.id.progress)
        ivProgressError = v.findViewById(R.id.ly_error)
        tvMessage = v.findViewById(R.id.tv_message)

        addView(v)
    }

    fun setRetryClickListener(onClickListener: View.OnClickListener) {
        ivProgressError.setOnClickListener(onClickListener)
    }

    fun startProgress() {
        visibility = View.VISIBLE
        ivProgress.visibility = View.VISIBLE
        ivProgressError.visibility = View.GONE
        tvMessage.visibility = View.VISIBLE
    }

    fun stopAndGone() {
        visibility = View.GONE
    }

    fun stopAndError(errorMessage: String, isRetry: Boolean) {
        visibility = View.VISIBLE
        if (isRetry)
            ivProgressError.visibility = View.VISIBLE
        else
            ivProgressError.visibility = View.GONE
        tvMessage.text = errorMessage
    }

}
