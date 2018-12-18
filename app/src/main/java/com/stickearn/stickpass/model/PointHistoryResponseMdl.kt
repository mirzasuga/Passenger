package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class PointHistoryResponseMdl(@SerializedName("amount")
                                   val amount: Int = 0,
                                   @SerializedName("metadata")
                                   val metadata: String = "",
                                   @SerializedName("account_uuid")
                                   val accountUuid: String = "",
                                   @SerializedName("created_at")
                                   val createdAt: String = "",
                                   @SerializedName("source")
                                   val source: String = "",
                                   @SerializedName("uuid")
                                   val uuid: String = "",
                                   @SerializedName("type_transaction")
                                   val typeTransaction: String = "",
                                   @SerializedName("total_balance")
                                   val totalBalance: Int = 0,
                                   @SerializedName("user_uuid")
                                   val userUuid: String = "",
                                   @SerializedName("updated_at")
                                   val updatedAt: String = "",
                                   @SerializedName("transaction_number")
                                   val transactionNumber: String = "",
                                   @SerializedName("current_balance")
                                   val currentBalance: Int = 0,
                                   @SerializedName("action")
                                   val action: String = "",
                                   @SerializedName("id")
                                   val id: Int = 0,
                                   @SerializedName("status")
                                   val status: String = "")