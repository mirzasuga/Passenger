package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by macos-fajar on 29/03/18.
 */
data class SurveyResponseMdl(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("description")
        val description: String = "",
        @SerializedName("image_url")
        val imageUrl: String?=null,
        @SerializedName("point")
        val point: Int = 0,
        @SerializedName("time")
        val time: Int = 0,
        @SerializedName("total_question")
        val totalQuestion: Int = 0,
        @SerializedName("meta")
        val meta: String? = null,
        @SerializedName("target_surveyor")
        val targetSurveyor: String? = null,
        @SerializedName("depend_survey")
        val dependSurvey: String? = null,
        @SerializedName("related_survey")
        val relatedSurvey: String? = null,
        @SerializedName("start_date")
        val startDate: String = "",
        @SerializedName("end_date")
        val endDate: String = "",
        @SerializedName("created_by")
        val createdBy: String = "",
        @SerializedName("status")
        val status: Int = 0,
        @SerializedName("created_at")
        val createdAt: String = "",
        @SerializedName("updated_at")
        val updatedAt: String = "",
        @SerializedName("survey_uuid")
        val surveyUuid: String = "",
        @SerializedName("uuid")
        val uuid: String = ""): Serializable