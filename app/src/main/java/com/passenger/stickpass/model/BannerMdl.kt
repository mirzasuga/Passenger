package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by macos-vanya on 17/01/18.
 */
class BannerMdl (var id:Int
                 ,var imgUrl:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(imgUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BannerMdl> {
        override fun createFromParcel(parcel: Parcel): BannerMdl {
            return BannerMdl(parcel)
        }

        override fun newArray(size: Int): Array<BannerMdl?> {
            return arrayOfNulls(size)
        }
    }
}