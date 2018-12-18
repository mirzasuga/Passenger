package com.stickearn.stickpass.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by macos-vanya on 22/01/18.
 */
class MartResponseMdl(@SerializedName("price_point")
                      val pricePoint: Int = 0,
                      @SerializedName("imagesMdl")
                      val imagesMdl: ImagesMdl,
                      @SerializedName("main_image")
                      val mainImage: BaseImageMdl,
                      @SerializedName("created_at")
                      val createdAt: String = "",
                      @SerializedName("mart_box_id")
                      val martBoxId: Int = 0,
                      @SerializedName("product_name")
                      val productName: String = "",
                      @SerializedName("updated_at")
                      val updatedAt: String? = null,
                      @SerializedName("product_id")
                      val productId: Int = 0,
                      @SerializedName("category")
                      val category: String = "",
                      @SerializedName("product_description")
                      val productDescription: String = "",
                      @SerializedName("stock")
                      val stock: Int = 0,
                      @SerializedName("price_currency")
                      val priceCurrency: Int = 0,
                      @SerializedName("status")
                      val status: Int = 0): Serializable