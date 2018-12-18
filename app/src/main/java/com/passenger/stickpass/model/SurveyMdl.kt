package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by macos-vanya on 07/02/18.
 */
data class SurveyMdl(val id:Int?=0,
                     val name:String?=null,
                     val description:String?=null,
                     val point:Int?=0,
                     val date:String?=null,
                     val type:Int?=0,
                     val thumbnail : Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeValue(point)
        parcel.writeString(date)
        parcel.writeValue(type)
        parcel.writeInt(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SurveyMdl> {
        override fun createFromParcel(parcel: Parcel): SurveyMdl {
            return SurveyMdl(parcel)
        }

        override fun newArray(size: Int): Array<SurveyMdl?> {
            return arrayOfNulls(size)
        }
    }
}