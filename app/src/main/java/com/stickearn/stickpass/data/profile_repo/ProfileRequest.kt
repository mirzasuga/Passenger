package com.stickearn.stickpass.data.profile_repo

import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 2/26/18.
 */
interface ProfileRequest {

    fun loadProfile(auth:String,token:String): Observable<ProfileResponseMdl>
    fun editProfile(auth:String,token:String,data:EdiProfileRequestMdl): Observable<ProfileResponseMdl>
}