package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by macos-vanya on 18/01/18.
 */

class MenuMdl(val numOfSongs : Int, val thumbnail : Int, val name : String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(numOfSongs)
        parcel.writeInt(thumbnail)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuMdl> {
        override fun createFromParcel(parcel: Parcel): MenuMdl {
            return MenuMdl(parcel)
        }

        override fun newArray(size: Int): Array<MenuMdl?> {
            return arrayOfNulls(size)
        }
    }
}