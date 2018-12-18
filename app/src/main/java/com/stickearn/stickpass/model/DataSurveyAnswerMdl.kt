package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class DataSurveyAnswerMdl(@SerializedName("updated_at")
                    val updatedAt: String = "",
                               @SerializedName("answer_title")
                    val answerTitle: String = "",
                               @SerializedName("created_at")
                    val createdAt: String = "",
                               @SerializedName("id")
                    val id: Int = 0,
                               @SerializedName("answer_image")
                    val answerImage: String = "",
                               @SerializedName("question_id")
                    val questionId: Int = 0,
                               var clicked:Boolean = false)