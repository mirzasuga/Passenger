package com.stickearn.stickpass.view.edit_profile

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.profile_repo.ProfileRepository
import com.stickearn.stickpass.model.EdiProfileRequestMdl
import com.stickearn.stickpass.model.ProfileResponseMdl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by macos-vanya on 09/02/18.
 */
class EditProfilePresenter(mView: EditProfileView) : BasePresenter<EditProfileView>(mView) {

    val mRequest = ProfileRepository()
    fun postEditProfile(token:String,auth:String,data:EdiProfileRequestMdl){
        mView.showLoading()
        mRequest.editProfile(auth,token,data).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<ProfileResponseMdl>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: ProfileResponseMdl) {
                        mView.stopLoading()
                        mView.onEditProfileSuccess(result)
                    }
                })

    }
}