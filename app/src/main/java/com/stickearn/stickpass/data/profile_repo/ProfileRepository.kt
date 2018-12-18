package com.stickearn.stickpass.data.profile_repo

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 2/26/18.
 */
class ProfileRepository :ProfileRequest, BaseRepository() {
    override fun editProfile(auth: String, token: String, data: EdiProfileRequestMdl): Observable<ProfileResponseMdl> {
        val mData  = PayloadRequestBaseMdl(data)
        return mApiClient.getApiServices().postEditProfile(token,auth,mData).flatMap { Observable.just(it.data) }
    }

    override fun loadProfile(auth: String, token: String): Observable<ProfileResponseMdl> {

        return mApiClient.getApiServices().getProfile(token,auth).flatMap { Observable.just(it.data) }
    }



}