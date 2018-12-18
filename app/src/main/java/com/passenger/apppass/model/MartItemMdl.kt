package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MartItemMdl(@SerializedName("subtotal_price")
                        val subtotalPrice: Int = 0,
                       @SerializedName("product_id")
                        val productId: Int = 0,
                       val qty: Int = 0,
                       @SerializedName("subtotal_point")
                        val subtotalPoint: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(subtotalPrice)
        parcel.writeInt(productId)
        parcel.writeInt(qty)
        parcel.writeInt(subtotalPoint)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MartItemMdl> {
        override fun createFromParcel(parcel: Parcel): MartItemMdl {
            return MartItemMdl(parcel)
        }

        override fun newArray(size: Int): Array<MartItemMdl?> {
            return arrayOfNulls(size)
        }
    }
}