package com.stickearn.stickpass.view.survey_question

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.wtf
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.model.ChatMdl
import com.stickearn.stickpass.model.QuestionMdl
import com.stickearn.stickpass.utils.CustomView
import kotlinx.android.synthetic.main.survey_question_activity.*
import com.google.gson.Gson
import com.stickearn.stickpass.model.*
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.survey_complete.SurveyCompleteActivity
import com.stickearn.stickpass.view.survey.SurveyActivity
import org.jetbrains.anko.startActivity


class SurveyQuestionActivity : BaseActivity(),SurveyQuestionView,CustomView.AnswerClick {

    override fun onSuccessSubmitAnswer(result: BaseMdl<SurveyQuestionResponseMdl>) {
        finalQuestion = result.meta.final_question
        nextQuestion = result.meta.next_question_id
        questionId = result.data.id

        adapter.notifyDataSetChanged()

        if (!finalQuestion) {
            mPresenter.getNextQuestion(Utils.getAuth(this), Utils.getToken(this), mSurveyUuid, nextQuestion)
        }else{
            startActivity<SurveyCompleteActivity>()
            finish()
        }
    }

    override fun onSuccessLoadStartSurvey(result: BaseMdl<SurveyQuestionResponseMdl>) {
        wtf("data",Gson().toJson(result))
        val chatMdl = ChatMdl(1,result.data.title)
        listChat.add(chatMdl)
        answer.removeAllViews()
        setupAnswer(result)

        finalQuestion = result.meta.final_question
        nextQuestion = result.meta.next_question_id
        questionId = result.data.id

        adapter.notifyDataSetChanged()
    }

    var questionId:Int = 0

    override fun onAnswerClick(result: String, answerId:String) {
        wtf("answer",result)
        val chatMdl = ChatMdl(2,result)
        listChat.add(chatMdl)

//        setupAnswer(dummyData)
        adapter.notifyDataSetChanged()
        rvChat.smoothScrollToPosition(rvChat.adapter.itemCount)

    }

    var listChat :MutableList<ChatMdl> = mutableListOf()
    lateinit var adapter:QuestionAdapter
    private lateinit var mPresenter:SurveyQuestionPresenter
    var nextQuestion:Int=1

    override fun showLoading() {
        val chatMdl = ChatMdl(3,"loading")
        listChat.add(chatMdl )
        adapter.notifyItemInserted(listChat.size - 1)


    }

    override fun errorLoading(errorMessage: String?) {
        if (errorMessage.equals("habis")){
            startActivity<SurveyCompleteActivity>()
            finish()
        }

    }

    override fun stopLoading() {
        val chatMdl = ChatMdl(3,"loading")
        listChat.remove(chatMdl)
        adapter.notifyItemRemoved(listChat.size - 1)
        adapter.setLoaded()
    }

    var finalQuestion:Boolean = false

    override fun onSuccessLoadSurveyQuestion(result: BaseMdl<SurveyQuestionResponseMdl>) {
        wtf("data",Gson().toJson(result))
        val chatMdl = ChatMdl(1,result.data.title)
        listChat.add(chatMdl)
        answer.removeAllViews()
        setupAnswer(result)

        finalQuestion = result.meta.final_question
        nextQuestion = result.meta.next_question_id
        questionId = result.data.id

        adapter.notifyDataSetChanged()
    }

    /*override fun displayQuestion(dummyData: QuestionMdl) {
        wtf("data",Gson().toJson(dummyData))
        val chatMdl = ChatMdl(1,dummyData.questionText)
        listChat.add(chatMdl)
        answer.removeAllViews()
        setupAnswer(dummyData)
        adapter.notifyDataSetChanged()
    }*/

    val TAG_UUID_QUESTION = "uuidQuestion"

    fun startThisActivity(context: Context, surveyUuid:String){
        val intent= Intent(context, SurveyQuestionActivity::class.java)

        intent.putExtra(TAG_UUID_QUESTION,surveyUuid)
        context.startActivity(intent)
    }

    private fun setupAnswer(dummyData: BaseMdl<SurveyQuestionResponseMdl>) {

        when(dummyData.data.answerType){
            /*1 ->{
                answer.addView(CustomView().displayRating(this,this))
            }
            2 ->{
                answer.addView(CustomView().displayYesNo(this,this,dummyData.questionOption))
            }
            3 ->{
                answer.addView(CustomView().displayText(this,this,dummyData.subType,rvChat))
                rvChat.scrollToPosition(listChat.size-1)
            }*/
            "single choice" ->{
                answer.addView(CustomView().displayMultipleChoice(this,this,Gson().toJson(dummyData.data.answerChoice.data),1))
            }
            /*5 ->{
                answer.addView(CustomView().displayImageChoice(this,this,Gson().toJson(dummyData.questionOption),dummyData.subType))
            }*/
        }

    }

    private fun setupAnswer(dummyData: QuestionMdl) {

        when(dummyData.type){
        1 ->{
            answer.addView(CustomView().displayRating(this,this))
        }
        2 ->{
            answer.addView(CustomView().displayYesNo(this,this,dummyData.questionOption))
        }
        3 ->{
            answer.addView(CustomView().displayText(this,this,dummyData.subType,rvChat))
            rvChat.scrollToPosition(listChat.size-1)
        }
            4 ->{
                answer.addView(CustomView().displayMultipleChoice(this,this,Gson().toJson(dummyData.questionOption),dummyData.subType))
            }
        5 ->{
            answer.addView(CustomView().displayImageChoice(this,this,Gson().toJson(dummyData.questionOption),dummyData.subType))
        }
        }

    }

    lateinit var mSurveyUuid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.survey_question_activity)
        initListChat()
        mPresenter = SurveyQuestionPresenter(this)

        mSurveyUuid = intent.getStringExtra(TAG_UUID_QUESTION)

//        mPresenter.getNextQuestion(Utils.getAuth(this),Utils.getToken(this),mSurveyUuid, questionNumber)
        mPresenter.startSurvey(Utils.getAuth(this),Utils.getToken(this),PayloadRequestBaseMdl(SurveyRequestMdl(mSurveyUuid)))

//        mPresenter.getData(questionNumber)
    }

    private fun initListChat() {
        rvChat.layoutManager = LinearLayoutManager(this)
        adapter = QuestionAdapter(this,listChat)
        rvChat.adapter = adapter
        rvChat.scrollToPosition(listChat.size-1)

    }

    override fun onBackPressed() {
        displayDialogAlert()
    }

    private fun displayDialogAlert() {
        val alertDIalog=AlertDialog.Builder(this)
        alertDIalog.setTitle("Alert")
        alertDIalog.setMessage("Are you sure quit this survey ?")
        alertDIalog.setPositiveButton("Quit",DialogInterface.OnClickListener { dialog, which ->
            startActivity<SurveyActivity>()
            finish()
        })
        alertDIalog.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        alertDIalog.show()
    }
}
