package com.stickearn.stickpass.view.survey_detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log.wtf
import android.view.View
import android.widget.LinearLayout
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.model.SurveyResponseMdl
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformation
import com.stickearn.stickpass.view.survey_question.SurveyQuestionActivity
import kotlinx.android.synthetic.main.activity_survey_detail.*
import okhttp3.internal.Util
import org.jetbrains.anko.startActivity

class SurveyDetailActivity : BaseActivity(), SurveyDetailView {
    override fun showLoading() {
        if(progressBar.visibility== View.GONE){
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun errorLoading(errorMessage: String?) {

    }

    override fun stopLoading() {
        if(progressBar.visibility==View.VISIBLE){
            progressBar.visibility = View.GONE
        }
    }

    override fun onSuccessLoadSurveyDetail(result: SurveyResponseMdl) {
        mData = result
        initSurveyDetail(result)
    }

    private fun initSurveyDetail(result: SurveyResponseMdl){
        tv_survey_title.text = result.title
        tv_point_survey.text = result.point.toString()
        tv_question_survey.text = result.totalQuestion.toString()
        tv_survey_time.text = result.time.toString()
        tv_desc.text = Html.fromHtml(result.description)
        iv_detail.loadImageWithPicassoTransformation(result.imageUrl.toString())
    }

    val TAG_SURVEY_UUID = "surveyUuid"

    fun startThisActivity(context: Context,surveyUuid:String){
        val intent=Intent(context, SurveyDetailActivity::class.java)

        intent.putExtra(TAG_SURVEY_UUID,surveyUuid)
        context.startActivity(intent)
    }

    lateinit var progressBar: LinearLayout
    lateinit var mPresenter:SurveyDetailPresenter
    lateinit var mData: SurveyResponseMdl
    lateinit var mSurveyUuid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_detail)

        progressBar = findViewById(R.id.progress_dialog)
        mSurveyUuid= intent.getStringExtra(TAG_SURVEY_UUID)
        mPresenter = SurveyDetailPresenter(this)
        wtf("auth", Utils.getAuth(this))
        wtf("getToken", Utils.getToken(this))
        mPresenter.getSurveyDetail( Utils.getAuth(this), Utils.getToken(this), mSurveyUuid)
        btn_start_survey.setOnClickListener{
            SurveyQuestionActivity().startThisActivity(this,mSurveyUuid)
        }
    }

    /*fun startThisActivity(context: Context){
        val intent = Intent(context, SurveyDetailActivity::class.java)
        context.startActivity(intent)
    }*/
}
