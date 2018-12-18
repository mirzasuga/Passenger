package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class DataImageMdl(@SerializedName("image_description")
                val imageDescription: String? = null,
                        @SerializedName("extension")
                val extension: String? = null,
                        @SerializedName("default")
                val default: Int = 0,
                        @SerializedName("updated_at")
                val updatedAt: String? = null,
                        @SerializedName("image_url")
                val imageUrl: String = "",
                        @SerializedName("image_path")
                val imagePath: String = "",
                        @SerializedName("mime")
                val mime: String? = null,
                        @SerializedName("width")
                val width: String? = null,
                        @SerializedName("created_at")
                val createdAt: String = "",
                        @SerializedName("id")
                val id: Int = 0,
                        @SerializedName("priority")
                val priority: Int = 0,
                        @SerializedName("height")
                val height: String? = null)