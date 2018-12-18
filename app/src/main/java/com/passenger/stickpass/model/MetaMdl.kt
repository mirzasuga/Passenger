package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class MetaMdl(@SerializedName("total")
                   val total: Int = 0,
                   @SerializedName("count")
                   val count: Int = 0,
                   @SerializedName("limit")
                   val limit: Int = 0,
                   @SerializedName("max_page")
                   val maxPage: Int = 0,
                   @SerializedName("current_page")
                   val currentPage: Int = 0,
                   @SerializedName("total_read")
                   val totalRead: Int = 0,
                   @SerializedName("final_question")
                   val final_question: Boolean,
                   @SerializedName("next_question_id")
                   val next_question_id: Int = 0,
                   @SerializedName("total_unread")
                   val totalUnread: Int = 0)