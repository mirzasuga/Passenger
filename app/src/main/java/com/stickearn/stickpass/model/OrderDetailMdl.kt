package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

/**
 * Created by macos-vanya on 03/04/18.
 */
class OrderDetailMdl (@SerializedName("driver_id")
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
                      val status: String = "",
                      @SerializedName("items")
                      val items: ItemsOrderDetailMdl)