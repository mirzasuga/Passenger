package com.stickearn.stickpass.data.onboarding_repo

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.BankMdl
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.ExampleMdl
import com.stickearn.stickpass.model.OnBoardingMdl
import io.reactivex.Observable


/**
 * Created by oohyugi on 1/15/18.
 */
interface OnboardingRequest : BaseRequest {



        fun loadOnboarding(): Observable<List<OnBoardingMdl>>

}