package com.stickearn.stickpass.utils

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.text.TextUtils
import android.util.Log.wtf
import android.view.View
import com.stickearn.stickpass.R
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.stickearn.stickpass.model.DataSurveyAnswerMdl
import com.stickearn.stickpass.model.QuestionOptionsMdl


/**
 * Created by oohyugi on 2/14/18.
 */
class CustomView:MultipleChoiceAdapter.MultipleSelected,ImageChoiceAdapter.ImageSelected {

    val listTextDisplay :MutableList<String> = mutableListOf()
    val listAnswerId :MutableList<String> = mutableListOf()
    var answer:String=""
    var answerId:String=""
    lateinit var answerClick: AnswerClick

    override fun onSelectedImage(itemSelected: MutableList<DataSurveyAnswerMdl>, subType: Int) {
        listTextDisplay.clear()
        itemSelected.indices.mapTo(listTextDisplay) { itemSelected[it].answerTitle }
        answer =listTextDisplay.joinToString(",")

        itemSelected.indices.mapTo(listAnswerId) { itemSelected[it].id.toString() }
        answerId =listAnswerId.joinToString(",")

        wtf("data display",Gson().toJson(listTextDisplay))
        wtf("list display",Gson().toJson(itemSelected))

        if (subType==1){
            answerClick.onAnswerClick(answer, answerId)
        }
    }

    override fun onSelected(itemSelected: MutableList<DataSurveyAnswerMdl>, subType: Int) {
        listTextDisplay.clear()
        itemSelected.indices.mapTo(listTextDisplay) { itemSelected[it].answerTitle }

        itemSelected.indices.mapTo(listAnswerId) { itemSelected[it].id.toString() }
        answerId =listAnswerId.joinToString(",")

        wtf("data display",Gson().toJson(listTextDisplay))
        wtf("list display",Gson().toJson(itemSelected))
        answer =listTextDisplay.joinToString(",")
        if (subType==1){
            answerClick.onAnswerClick(answer,answerId)
        }

    }

    fun displayRating(context: Context,answerClick: AnswerClick):View{
        this.answerClick = answerClick
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val addView = inflater.inflate(R.layout.survey_answer_rating, null)
        val ratingBar= addView.findViewById<RatingBar>(R.id.ratingbar)
        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, _, _ ->
            val ratInt:Int = ratingBar.rating.toInt()
            answer = ratInt.toString()
            answerClick.onAnswerClick(answer, answer)

        }
//        ratingBar.setOnRatingBarChangeListener() {
//
//        }

        return addView

    }

    fun displayYesNo(context: Context, answerClick: AnswerClick, questionOption: List<QuestionOptionsMdl>?):View{
//        this.answerClick = answerClick
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val addView = inflater.inflate(R.layout.survey_answer_yesno, null)
        val btnYes =addView.findViewById<Button>(R.id.btnYes)
        val btnNo =addView.findViewById<Button>(R.id.btnNo)
        btnYes.text = questionOption!![0].displayText.toString()
        btnNo.text = questionOption!![1].displayText.toString()
        btnYes.setOnClickListener {
            answer = questionOption!![0].displayText.toString()
            answerClick.onAnswerClick(answer,"1")
        }
        btnNo.setOnClickListener {
            answer = questionOption!![1].displayText.toString()
            answerClick.onAnswerClick(answer,"2")
        }

        return addView

    }
    fun displayText(context: Context,answerClick: AnswerClick,subType:Int, recyclerView: RecyclerView):View{
//        this.answerClick = answerClick
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val addView = inflater.inflate(R.layout.survey_answer_text, null)
        val btnSend = addView.findViewById<Button>(R.id.btnSend)
        val etAnswer = addView.findViewById<EditText>(R.id.etAnswer)
        when (subType) {
            1 -> etAnswer.inputType = InputType.TYPE_CLASS_TEXT
            2 -> etAnswer.inputType = InputType.TYPE_CLASS_NUMBER
            3 -> etAnswer.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
//        etAnswer.focusable
//        etAnswer.focusable
        etAnswer.isFocusable=true
        etAnswer.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            run {
                if (hasFocus) {

                    recyclerView.smoothScrollToPosition(recyclerView.adapter.itemCount);
                }
            }
        }

        btnSend.setOnClickListener {
            val activity = context as Activity?
            val view = activity!!.currentFocus
            if (view != null) {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view!!.windowToken, 0)
            }
            if (TextUtils.isEmpty(etAnswer.text.toString())){
                etAnswer.error = context.getString(R.string.empty_input_answer)
            }else{
                answer = etAnswer.text.toString()
                answerClick.onAnswerClick(answer,"0")
            }


        }
        return addView

    }

    lateinit var adapter : MultipleChoiceAdapter
    fun displayMultipleChoice(context: Context, answerClick: AnswerClick, item: String, subType: Int):View{
        this.answerClick = answerClick
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val addView = inflater.inflate(R.layout.survey_answer_multiple_choice, null)
        val rv = addView.findViewById<RecyclerView>(R.id.rvMultipleChoice)
        val btnSend = addView.findViewById<Button>(R.id.btnSend)

        adapter = MultipleChoiceAdapter(context,getListFromJsonString(item),this,subType)
        rv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rv.adapter = adapter
        val data =getListFromJsonString(item)
        if (subType==1){
            btnSend.visibility=View.GONE
        }else{
            btnSend.visibility=View.VISIBLE
            btnSend.setOnClickListener {

//                wtf("data multiple",answer)

                if (answer != ""){
                    answerClick.onAnswerClick(answer,"0")
                }else{
                    Toast.makeText(context,"Please select answer",Toast.LENGTH_LONG).show()
                }

            }
        }

        return addView

    }

    lateinit var imgAdapter : ImageChoiceAdapter
    fun displayImageChoice(context: Context, answerClick: AnswerClick, item: String, subType: Int):View{
        this.answerClick = answerClick
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val addView = inflater.inflate(R.layout.survey_answer_multiple_choice, null)
        val rv = addView.findViewById<RecyclerView>(R.id.rvMultipleChoice)
        val btnSend = addView.findViewById<Button>(R.id.btnSend)

        imgAdapter = ImageChoiceAdapter(context,getListFromJsonString(item),this,subType)
        rv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rv.adapter = imgAdapter
        val data =getListFromJsonString(item)
        if (subType==1){
            btnSend.visibility=View.GONE
        }else{
            btnSend.visibility=View.VISIBLE
            btnSend.setOnClickListener {

                if (answer != ""){
                    answerClick.onAnswerClick(answer,"0")
                }else{
                    Toast.makeText(context,"Please select answer",Toast.LENGTH_LONG).show()
                }
            }
        }
        return addView

    }


    fun getListFromJsonString(json: String): List<DataSurveyAnswerMdl> {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        val typeOfList = object : TypeToken<List<DataSurveyAnswerMdl>>() {

        }.type
        return gson.fromJson(json, typeOfList)

    }



    interface AnswerClick{
        fun onAnswerClick(result:String, answerId:String)

    }
}