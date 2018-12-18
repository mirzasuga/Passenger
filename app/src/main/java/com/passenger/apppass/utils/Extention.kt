package com.stickearn.stickpass.utils

import android.net.Uri
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.util.regex.Pattern

/**
 * Created by oohyugi on 1/15/18.
 */

fun String.getData(number:Int):String{
    return "WWWW"
}
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImageWithPicasso(urlImage:String){
    Picasso.with(context).load(urlImage).into(this)
}
fun ImageView.loadImageWithPicassoCenterCrop(urlImage:String){
    Picasso.with(context).load(urlImage).resize(300,300).centerCrop().into(this)
}
fun ImageView.loadImageWithPicassoFit(urlImage:String){
    Picasso.with(context).load(urlImage).resize(300,240).into(this)
}
fun ImageView.loadImageWithPicassoResize(urlImage:String){
    Picasso.with(context).load(urlImage).resize(500,0).into(this)
}

fun ImageView.loadImageWithPicassoTransformation(urlImage:String){
    Picasso.with(context).load(urlImage).transform(ScaleToFitWidthHeightTransform(800,true)).fit().into(this)
}
fun ImageView.loadImageWithPicassoTransformationMartItem(urlImage:String){
    Picasso.with(context).load(urlImage).transform(ScaleToFitWidthHeightTransform(800,true)).into(this)
}
fun ImageView.loadImageWithPicassoTransformation(urlImage:Int){
    Picasso.with(context).load(urlImage).transform(ScaleToFitWidthHeightTransform(800,true)).into(this)
}
fun ImageView.loadImageWithPicasso(urlImage: Uri){
    Picasso.with(context).load(urlImage).into(this)
}
fun ImageView.loadImageWithPicasso(urlImage: Int){
    Picasso.with(context).load(urlImage).into(this)
}

fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(left, top, right, bottom)
        this.requestLayout()
    }
}

fun String.getStringBefore(before:String):String{
    val posA = this.indexOf(before)
    return if (posA == -1) {
        this
    } else this.substring(0, posA)
}
fun String.getStringAfter(after:String):String{
    val posA = this.lastIndexOf(after)
    if (posA == -1) {
        return ""
    }
    val adjustedPosA = posA + after.length
    return if (adjustedPosA >=this.length) {
        ""
    } else this.substring(adjustedPosA)
}

