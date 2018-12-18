package com.stickearn.stickpass.data.main_repo

import com.stickearn.stickpass.R
import com.stickearn.stickpass.StickPassAPP
import com.stickearn.stickpass.api.ApiClient
import com.stickearn.stickpass.model.*
import io.reactivex.Observable


/**
 * Created by oohyugi on 1/15/18.
 */
class MainRepository : MainRequest {
    override fun loadMenu(): Observable<List<MenuMdl>> {
        return Observable.just(getDummyMenu())
    }

    private fun getDummyMenu(): List<MenuMdl>? {
        val  mList= mutableListOf<MenuMdl>()

        mList.add(MenuMdl(1, R.drawable.ic_checked_note, "Stick Survey"))
        mList.add(MenuMdl(2, R.drawable.ic_cart, "Stick Mart"))
        mList.add(MenuMdl(3, R.drawable.ic_help_button, "FAQ"))
        mList.add(MenuMdl(4, R.drawable.ic_infoo, "Privacy Policy"))
        return mList
    }

    override fun loadBanner(): Observable<List<BannerMdl>> {
        return Observable.just(getDummyBanner())
    }

    private fun getDummyBanner(): MutableList<BannerMdl> {
        val  mList= mutableListOf<BannerMdl>()

        mList.add(BannerMdl(1, "https://siva.jsstatic.com/id/62043/imagesMdl/banner/62043_banner_0_450752.jpg"))
        mList.add(BannerMdl(1, "http://www.digination.id/article%20banner/-2017-09-04-%20Stickearn-%20Startup%20Anak%20Negeri%20Hadirkan%20Solusi%20Jitu%20Iklan%20Luar%20Ruang.jpg"))
        return mList
    }

    override fun loadBank(id: Int): Observable<BaseMdl<List<BankMdl>>> {
        return mAPiClient.mApiServices.getBank(1).flatMap { Observable.just(it) }
    }

    var mAPiClient : ApiClient = ApiClient("http://54.68.121.149:8091/api/")
//    override fun loadExample(): Observable<List<ExampleMdl>> {
////        return Observable.just(getDummy())
//        return mAPiClient.getApiServices().getDataExample()
//                .flatMap { Observable.just(it) }
//
//
//
//    }

    private fun getDummy(): List<ExampleMdl>? {
       val list =mutableListOf<ExampleMdl>()
        list.add(ExampleMdl(1, "test"))

        return list
    }




}