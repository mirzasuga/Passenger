package com.stickearn.stickpass.data.history_repo

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.HistoryMdl
import com.stickearn.stickpass.model.MenuMdl
import com.stickearn.stickpass.model.PointHistoryResponseMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 2/1/18.
 */
interface HistoryRequest:BaseRequest {

    fun loadData(): Observable<List<HistoryMdl>>
    fun getPointHistory(auth:String,token:String,uuidPoint:String,page:Int):Observable<BaseMdl<List<PointHistoryResponseMdl>>>
}