package com.stickearn.stickpass.model

/**
 * Created by oohyugi on 2/14/18.
 */
data class QuestionMdl (
        val questionId:Int=0,
        val type:Int=0,
        val subType:Int=0,
        val questionText:String?=null,
        val questionOption:List<QuestionOptionsMdl>?=null

)