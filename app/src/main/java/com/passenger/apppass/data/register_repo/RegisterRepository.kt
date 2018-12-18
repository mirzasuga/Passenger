package com.stickearn.stickpass.data.register_repo

import com.stickearn.stickpass.api.ApiClient
import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.helper.Constant
import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 3/8/18.
 */
class RegisterRepository :BaseRepository(),RegisterRequest {
    override fun postRegister(registerRequestMdl: RegisterRequestMdl): Observable<BaseMdl<LoginResponseMdl>> {
        val mClient = ApiClient(Constant.BASE_URL_AUTH)
        return mClient.mApiServices.postRegister(registerRequestMdl).flatMap { Observable.just(it) }
    }
}