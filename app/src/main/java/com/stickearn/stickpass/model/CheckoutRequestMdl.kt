package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oohyugi on 2/7/18.
 */

data class CheckoutRequestMdl(@SerializedName("user_uuid")
                              val userUuid: String? = null,
                              @SerializedName("grand_total_point")
                              val grandTotalPoint: Int = 0,
                              @SerializedName("mart_item")
                              val martItem: List<MartItemMdl>?,
                              @SerializedName("grand_total_price")
                              val grandTotalPrice: Int = 0,
                              @SerializedName("mart_id_display")
                              val martIdDisplay: String = "",
                              @SerializedName("payment_method")
                              val paymentMethod: String = "")