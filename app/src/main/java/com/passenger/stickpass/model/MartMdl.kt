package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by macos-vanya on 22/01/18.
 */
class MartMdl (@SerializedName("price_point")
               val pricePoint: Int = 0,
               @SerializedName("updated_at")
               val updatedAt:String? = null,
               @SerializedName("product_id")
               val productId: Int = 0,
               @SerializedName("created_at")
               val createdAt: String = "",
               @SerializedName("mart_box_id")
               val martBoxId: Int = 0,
               val category: String = "",
               @SerializedName("product_description")
               val productDescription: String = "",
               val stock: Int = 0,
               @SerializedName("product_name")
               val productName: String = "",
               @SerializedName("price_currency")
               val priceCurrency: Int = 0,
               val status: Int = 0,
               @SerializedName("image_url")
               val imgUrl: String? = null,
               val qty:Int =0,
               val totalPrice: Int =0,
               val totalPoint: Int =0)
    : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pricePoint)
        parcel.writeString(updatedAt)
        parcel.writeInt(productId)
        parcel.writeString(createdAt)
        parcel.writeInt(martBoxId)
        parcel.writeString(category)
        parcel.writeString(productDescription)
        parcel.writeInt(stock)
        parcel.writeString(productName)
        parcel.writeInt(priceCurrency)
        parcel.writeInt(status)
        parcel.writeString(imgUrl)
        parcel.writeInt(qty)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MartMdl> {
        override fun createFromParcel(parcel: Parcel): MartMdl {
            return MartMdl(parcel)
        }

        override fun newArray(size: Int): Array<MartMdl?> {
            return arrayOfNulls(size)
        }
    }
}
