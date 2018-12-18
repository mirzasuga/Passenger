package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class PurchasedResponseMdl(@SerializedName("amount")
                                val amount: String = "",
                                @SerializedName("total_voucher")
                                val totalVoucher: Int = 0,
                                @SerializedName("account_uuid")
                                val accountUuid: String = "",
                                @SerializedName("current_voucher")
                                val currentVoucher: Int = 0,
                                @SerializedName("created_at")
                                val createdAt: String = "",
                                @SerializedName("source")
                                val source: String = "",
                                @SerializedName("uuid")
                                val uuid: String = "",
                                @SerializedName("type_transaction")
                                val typeTransaction: String = "",
                                @SerializedName("user_uuid")
                                val userUuid: String = "",
                                @SerializedName("updated_at")
                                val updatedAt: String = "",
                                @SerializedName("transaction_number")
                                val transactionNumber: String = "",
                                @SerializedName("voucher_id")
                                val voucherId: String = "",
                                @SerializedName("action")
                                val action: String = "",
                                @SerializedName("id")
                                val id: Int = 0)