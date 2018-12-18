package com.stickearn.stickpass.data.policy

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.PolicyMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 3/19/18.
 */
interface PolicyRequest:BaseRequest {

    fun getPolicy(auth:String,token:String,type:Int):Observable<PolicyMdl>
}
