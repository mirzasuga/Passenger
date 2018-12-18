package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by oohyugi on 1/17/18.
 */
data class OnBoardingMdl(var id:Int
                         ,var imgUrl:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(imgUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OnBoardingMdl> {
        override fun createFromParcel(parcel: Parcel): OnBoardingMdl {
            return OnBoardingMdl(parcel)
        }

        override fun newArray(size: Int): Array<OnBoardingMdl?> {
            return arrayOfNulls(size)
        }
    }
}