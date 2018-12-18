package com.stickearn.stickpass.utils

import android.graphics.Bitmap
import com.squareup.picasso.Transformation


/**
 * Created by oohyugi on 3/12/18.
 */
class ScaleToFitWidthHeightTransform(private val mSize: Int, private val isHeightScale: Boolean) : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val scale: Float
        val newSize: Int
        val scaleBitmap: Bitmap
        if (isHeightScale) {
            scale = mSize.toFloat() / source.height
            newSize = Math.round(source.width * scale)
            scaleBitmap = Bitmap.createScaledBitmap(source, newSize, mSize, true)
        } else {
            scale = mSize.toFloat() / source.width
            newSize = Math.round(source.height * scale)
            scaleBitmap = Bitmap.createScaledBitmap(source, mSize, newSize, true)
        }

        if (scaleBitmap != source) {
            source.recycle()
        }

        return scaleBitmap

    }

    override fun key(): String {
        return "scaleRespectRatio" + mSize + isHeightScale
    }
}