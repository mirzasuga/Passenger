package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class MartHistoryDataItemResponseMdl(@SerializedName("driver_id")
                    val driverId: Int = 0,
                                          @SerializedName("price_point")
                    val pricePoint: Int = 0,
                                          @SerializedName("quantity")
                    val quantity: Int = 0,
                                          @SerializedName("driver_uuid")
                    val driverUuid: String = "",
                                          @SerializedName("mart_display_id")
                    val martDisplayId: String = "",
                                          @SerializedName("created_at")
                    val createdAt: String = "",
                                          @SerializedName("mart_box_id")
                    val martBoxId: Int = 0,
                                          @SerializedName("product_name")
                    val productName: String = "",
                                          @SerializedName("user_uuid")
                    val userUuid: String = "",
                                          @SerializedName("subtotal_price_currency")
                    val subtotalPriceCurrency: Int = 0,
                                          @SerializedName("updated_at")
                    val updatedAt: String = "",
                                          @SerializedName("user_id")
                    val userId: Int = 0,
                                          @SerializedName("product_id")
                    val productId: Int = 0,
                                          @SerializedName("id")
                    val id: Int = 0,
                                          @SerializedName("subtotal_price_point")
                    val subtotalPricePoint: Int = 0,
                                          @SerializedName("price_currency")
                    val priceCurrency: Int = 0)