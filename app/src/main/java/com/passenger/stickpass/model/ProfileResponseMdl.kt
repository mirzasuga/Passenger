package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProfileResponseMdl(@SerializedName("birthday")
                              val birthday: String = "",
                              @SerializedName("image")
                              val image: String = "",
                              @SerializedName("address")
                              val address: String = "",
                              @SerializedName("is_active")
                              val isActive: Int = 0,
                              @SerializedName("gender")
                              val gender: String = "",
                              @SerializedName("city")
                              val city: String = "",
                              @SerializedName("last_name")
                              val lastName: String = "",
                              @SerializedName("created_at")
                              val createdAt: String = "",
                              @SerializedName("industry")
                              val industry: String = "",
                              @SerializedName("uuid")
                              val uuid: String = "",
                              @SerializedName("url")
                              val url: String = "",
                              @SerializedName("is_blocked")
                              val isBlocked: Int = 0,
                              @SerializedName("updated_at")
                              val updatedAt: String = "",
                              @SerializedName("phone")
                              val phone: String = "",
                              @SerializedName("marriage")
                              val marriage: String = "",
                              @SerializedName("id")
                              val id: Int = 0,
                              @SerializedName("salutation")
                              val salutation: String = "",
                              @SerializedName("postal_code")
                              val postalCode: String = "",
                              @SerializedName("job")
                              val job: String = "",
                              @SerializedName("first_name")
                              val firstName: String = "",
                              @SerializedName("name")
                              val name: String? = null,
                              @SerializedName("email")
                              val email: String = "",
                              @SerializedName("is_qualified")
                              val isQualified: Int = 0):Serializable