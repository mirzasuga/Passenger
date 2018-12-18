package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by oohyugi on 1/16/18.
 */
data class UserMdl
(@SerializedName("accessMdls")
 var accessMdls: List<AccessItemMdl>?=null,
 @SerializedName("phone")
 var phone: String? = null,
 @SerializedName("name")
 var name: String? = null,
 @SerializedName("active")
 var active: Int = 0,
 @SerializedName("id")
 var id: String = "",
 @SerializedName("email")
 var email: String?=null,
 @SerializedName("uuid")
 var uuid:String?=null
 ):Serializable