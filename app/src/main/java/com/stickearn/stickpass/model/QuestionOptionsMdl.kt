package com.stickearn.stickpass.model

import android.content.ClipData.Item



/**
 * Created by oohyugi on 2/14/18.
 */
data class QuestionOptionsMdl(
        var value:String?=null,
        var img_url:String?=null,
        var displayText:String?=null,
        var clicked:Boolean?=false

)
