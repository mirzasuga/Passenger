package com.stickearn.stickpass.view.order_failed

import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stickearn.stickpass.R
import com.stickearn.stickpass.view.main.MainActivity
import com.stickearn.stickpass.view.mart.MartActivity
import kotlinx.android.synthetic.main.order_complete_activity.*
import kotlinx.android.synthetic.main.order_failed_activity.*
import org.jetbrains.anko.startActivity

class OrderFailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_failed_activity)

        btn_cancel_order.setOnClickListener{
            startActivity<MainActivity>()
            finish()
        }

        btn_try_order.setOnClickListener{
            startActivity<MartActivity>()
            finish()
        }

        startCheckAnimation()
    }

    private fun startCheckAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500)
        animator.addUpdateListener { valueAnimator -> lottieFailed.setProgress(valueAnimator.animatedValue as Float) }

        if (lottieFailed.getProgress() === 0f) {
            animator.setDuration(3000)
        } else {
            lottieFailed.setProgress(0f)
        }
    }

    override fun onBackPressed() {
        startActivity<MainActivity>()
        finish()
    }
}
