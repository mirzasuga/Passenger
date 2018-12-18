package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class CartTest(@SerializedName("price_point")
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
                    val status: Int = 0)