package com.stickearn.stickpass.view.onboarding

import com.stickearn.stickpass.model.OnBoardingMdl

/**
 * Created by oohyugi on 2/8/18.
 */
interface OnboardingView {
    fun onLoadedDataSuccess(t: List<OnBoardingMdl>)
}