package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CheckoutResponseMdl(@SerializedName("driver_id")
                               val driverId: Int = 0,
                               @SerializedName("grand_total_price_currency")
                               val grandTotalPriceCurrency: Int = 0,
                               @SerializedName("purchase_id")
                               val purchaseId: String = "",
                               @SerializedName("driver_uuid")
                               val driverUuid: String = "",
                               @SerializedName("grand_total_price_point")
                               val grandTotalPricePoint: Int = 0,
                               @SerializedName("mart_display_id")
                               val martDisplayId: String = "",
                               @SerializedName("created_at")
                               val createdAt: String = "",
                               @SerializedName("mart_box_id")
                               val martBoxId: Int = 0,
                               @SerializedName("user_uuid")
                               val userUuid: String = "",
                               @SerializedName("subtotal_price_currency")
                               val subtotalPriceCurrency: Int = 0,
                               @SerializedName("updated_at")
                               val updatedAt: String = "",
                               @SerializedName("user_id")
                               val userId: String = "",
                               @SerializedName("order_uuid")
                               val orderUuid: String = "",
                               @SerializedName("id")
                               val id: Int = 0,
                               @SerializedName("subtotal_price_point")
                               val subtotalPricePoint: Int = 0,
                               @SerializedName("payment_method")
                               val paymentMethod: String = "",
                               @SerializedName("status")
                               val status: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(driverId)
        parcel.writeInt(grandTotalPriceCurrency)
        parcel.writeString(purchaseId)
        parcel.writeString(driverUuid)
        parcel.writeInt(grandTotalPricePoint)
        parcel.writeString(martDisplayId)
        parcel.writeString(createdAt)
        parcel.writeInt(martBoxId)
        parcel.writeString(userUuid)
        parcel.writeInt(subtotalPriceCurrency)
        parcel.writeString(updatedAt)
        parcel.writeString(userId)
        parcel.writeString(orderUuid)
        parcel.writeInt(id)
        parcel.writeInt(subtotalPricePoint)
        parcel.writeString(paymentMethod)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CheckoutResponseMdl> {
        override fun createFromParcel(parcel: Parcel): CheckoutResponseMdl {
            return CheckoutResponseMdl(parcel)
        }

        override fun newArray(size: Int): Array<CheckoutResponseMdl?> {
            return arrayOfNulls(size)
        }
    }
}