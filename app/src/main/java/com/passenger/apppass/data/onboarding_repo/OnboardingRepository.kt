package com.stickearn.stickpass.data.onboarding_repo

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.OnBoardingMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 1/17/18.
 */
class OnboardingRepository: OnboardingRequest {
    override fun loadOnboarding(): Observable<List<OnBoardingMdl>> {
        return Observable.just(getDummyOnboarding())
    }

    private fun getDummyOnboarding(): MutableList<OnBoardingMdl> {
        val  mList= mutableListOf<OnBoardingMdl>()

        mList.add(OnBoardingMdl(1, "https://encrypted-tbn0.gstatic.com/imagesMdl?q=tbn:ANd9GcSG556DRJvTRqr4eel-aa3KAtONr6wSYyyUddtb3kXo2YbfpE2ccA"))
        mList.add(OnBoardingMdl(2, "https://lh3.googleusercontent.com/GI07Vgm9DHm_s_jIZ0AQs77l6nPcZiMpJQmose9U_PmZRfc488p-J3sP6TIvy7ZZMnE=h900"))
        mList.add(OnBoardingMdl(3, "https://lh3.googleusercontent.com/9pz20R722Dl9JioPTEPycdfeyiBLMOHBdnGTMXyWhp5-1fiIZpN3am8zorrkavex8hWO=h310"))
        return mList
    }
}