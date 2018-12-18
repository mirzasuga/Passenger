package com.stickearn.stickpass.data.register_repo

import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 3/8/18.
 */
interface RegisterRequest {

    fun postRegister(registerRequestMdl: RegisterRequestMdl):Observable<BaseMdl<LoginResponseMdl>>?

}