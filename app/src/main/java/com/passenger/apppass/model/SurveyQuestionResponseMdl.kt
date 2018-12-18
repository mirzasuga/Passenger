package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class SurveyQuestionResponseMdl(@SerializedName("question_type")
                                     val questionType: String = "",
                                     @SerializedName("image_size")
                                     val imageSize: String = "",
                                     @SerializedName("depend_question_id")
                                     val dependQuestionId: String = "",
                                     @SerializedName("image_url")
                                     val imageUrl: String = "",
                                     @SerializedName("description")
                                     val description: String = "",
                                     @SerializedName("created_at")
                                     val createdAt: String = "",
                                     @SerializedName("image_width")
                                     val imageWidth: String = "",
                                     @SerializedName("title")
                                     val title: String = "",
                                     @SerializedName("created_by")
                                     val createdBy: String = "",
                                     @SerializedName("point")
                                     val point: String = "",
                                     @SerializedName("answer_choice")
                                     val answerChoice: AnswerChoiceMdl,
                                     @SerializedName("survey_id")
                                     val surveyId: Int = 0,
                                     @SerializedName("sequence")
                                     val sequence: Int = 0,
                                     @SerializedName("image_height")
                                     val imageHeight: String = "",
                                     @SerializedName("answer_type")
                                     val answerType: String = "",
                                     @SerializedName("updated_at")
                                     val updatedAt: String = "",
                                     @SerializedName("image_path")
                                     val imagePath: String = "",
                                     @SerializedName("jump_question_id")
                                     val jumpQuestionId: String = "",
                                     @SerializedName("id")
                                     val id: Int = 0,
                                     @SerializedName("image_type")
                                     val imageType: String = "",
                                     @SerializedName("status")
                                     val status: String = "")