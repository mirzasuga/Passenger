package com.stickearn.stickpass.data.main_repo

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.*
import io.reactivex.Observable


/**
 * Created by oohyugi on 1/15/18.
 */
interface MainRequest : BaseRequest {

        fun loadBank(id:Int): Observable<BaseMdl<List<BankMdl>>>
        fun loadBanner(): Observable<List<BannerMdl>>
        fun loadMenu(): Observable<List<MenuMdl>>

}