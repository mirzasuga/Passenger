package com.stickearn.stickpass.view.onboarding


import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter

import com.stickearn.stickpass.data.onboarding_repo.OnboardingRepository
import com.stickearn.stickpass.model.OnBoardingMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 1/17/18.
 */
class OnboardingPresenter(mView: OnboardingView) : BasePresenter<OnboardingView>(mView) {

    var mOnboardingReq = OnboardingRepository()
    fun loadData() {

        mOnboardingReq.loadOnboarding()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<OnBoardingMdl>>() {
                    override fun onResponseError(s: String) {

                    }

                    override fun onResponseSuccess(t: List<OnBoardingMdl>) {
                        mView.onLoadedDataSuccess(t)
                    }


                })
    }
}