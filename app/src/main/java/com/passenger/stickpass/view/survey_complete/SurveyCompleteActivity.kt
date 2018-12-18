package com.stickearn.stickpass.view.survey_complete

import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stickearn.stickpass.R
import com.stickearn.stickpass.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.survey_complete_activity.*
import org.jetbrains.anko.startActivity

class SurveyCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.survey_complete_activity)
        btnSurveyComplete.setOnClickListener {
            startActivity<SurveyActivity>()
            finish()
        }

        startCheckAnimation()

    }
    private fun startCheckAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500)
        animator.addUpdateListener { valueAnimator -> lottieAnimationView.progress = valueAnimator.animatedValue as Float }

        if (lottieAnimationView.progress === 0f) {
            animator.start()
            animator.duration = 3000
        } else {
            lottieAnimationView.progress = 0f
        }
    }
}
