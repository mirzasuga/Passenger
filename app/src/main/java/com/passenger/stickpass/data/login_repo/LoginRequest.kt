package com.stickearn.stickpass.data.login_repo

import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 3/8/18.
 */
interface LoginRequest {

    fun postAuth(loginRequestMdl: LoginRequestMdl):Observable<BaseMdl<LoginResponseMdl>>

}