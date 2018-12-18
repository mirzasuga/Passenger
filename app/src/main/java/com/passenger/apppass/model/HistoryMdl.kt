package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by oohyugi on 2/1/18.
 */
data class HistoryMdl (
        val id:Int?=0,
        val name:String?=null,
        val description:String?=null,
        val point:Int?=0,
        val date:String?=null,
        val type:Int?=0,
        val status:Int?=0
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeValue(point)
        parcel.writeString(date)
        parcel.writeValue(type)
        parcel.writeValue(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HistoryMdl> {
        override fun createFromParcel(parcel: Parcel): HistoryMdl {
            return HistoryMdl(parcel)
        }

        override fun newArray(size: Int): Array<HistoryMdl?> {
            return arrayOfNulls(size)
        }
    }
}