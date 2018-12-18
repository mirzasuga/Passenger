package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable

data class DataOrderDetailMdl(val driverId: Int = 0,
                              val pricePoint: Int = 0,
                              val quantity: Int = 0,
                              val driverUuid: String = "",
                              val martDisplayId: String = "",
                              val createdAt: String = "",
                              val martBoxId: Int = 0,
                              val product_name: String = "",
                              val userUuid: String = "",
                              val subtotalPriceCurrency: Int = 0,
                              val updatedAt: String = "",
                              val userId: Int = 0,
                              val productId: Int = 0,
                              val id: Int = 0,
                              val subtotalPricePoint: Int = 0,
                              val priceCurrency: Int = 0)