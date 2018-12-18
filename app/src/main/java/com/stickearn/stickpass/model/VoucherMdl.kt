package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VoucherMdl(@SerializedName("max_count")
                      val maxCount: Int = 0,
                      @SerializedName("validtodate")
                      val validtodate: String = "",
                      @SerializedName("used_date")
                      val usedDate: String? = null,
                      @SerializedName("amount")
                      val amount: Int = 0,
                      @SerializedName("usable_count")
                      val usableCount: Int = 0,
                      @SerializedName("updated_at")
                      val updatedAt: String = "",
                      @SerializedName("validfromdate")
                      val validfromdate: String = "",
                      @SerializedName("code_voucher")
                      val codeVoucher: String = "",
                      @SerializedName("image")
                      val image:String? =null,
                      @SerializedName("description")
                      val description:String?=null,
                      @SerializedName("created_at")
                      val createdAt: String = "",
                      @SerializedName("typeid")
                      val typeid: Int = 0,
                      @SerializedName("id")
                      val id: Int = 0,
                      @SerializedName("status")
                      val status: Int = 0,
                      @SerializedName("nama_voucher")
                      val namaVoucher:String?=null):Serializable