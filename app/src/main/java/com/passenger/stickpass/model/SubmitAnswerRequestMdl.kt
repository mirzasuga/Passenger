package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

/**
 * Created by macos-vanya on 09/04/18.
 */
data class SubmitAnswerRequestMdl (@SerializedName("survey_uuid")
                                   val survey_uuid: String,
                                   @SerializedName("question_id")
                                   val question_id: Int,
                                   @SerializedName("answer")
                                   val answer: String)