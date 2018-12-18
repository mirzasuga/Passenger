package com.stickearn.stickpass.data.policy

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.PolicyMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 3/19/18.
 */
class PolicyRepository: BaseRepository(),PolicyRequest {
     var mType : HashMap<String,Int> = HashMap()
    override fun getPolicy(auth: String, token: String,type:Int): Observable<PolicyMdl> {
       mType.put("type",type)
       return mApiClient.getApiServices().getPolicy(token,auth,mType).flatMap {
            Observable.just(it.data)
        }
    }
}