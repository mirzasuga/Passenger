package com.stickearn.stickpass.data.userhistory_repo

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.PointHistoryResponseMdl
import com.stickearn.stickpass.model.UserHistoryResponseMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 4/11/18.
 */
interface UserHistoryRequest:BaseRequest {

    fun getUserHistory(auth:String,token:String,page:Int): Observable<BaseMdl<List<UserHistoryResponseMdl>>>
}