package com.stickearn.stickpass.data.userhistory_repo

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.UserHistoryResponseMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 4/11/18.
 */
class UserHistoryRepository:BaseRepository(),UserHistoryRequest {
    override fun getUserHistory(auth: String, token: String, page: Int): Observable<BaseMdl<List<UserHistoryResponseMdl>>> {
        return mApiClient.getApiServices().getUserHistoryList(token,auth).flatMap { Observable.just(it) }
    }
}