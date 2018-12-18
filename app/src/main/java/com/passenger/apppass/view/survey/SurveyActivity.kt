package com.stickearn.stickpass.view.survey

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log.wtf
import com.google.gson.Gson
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.SurveyMdl
import com.stickearn.stickpass.model.SurveyResponseMdl
import kotlinx.android.synthetic.main.survey_activity.*

class SurveyActivity : BaseActivity(), SurveyView {
    override fun showLoading() {

    }

    override fun errorLoading(errorMessage: String?) {

    }

    override fun stopLoading() {

    }

    override fun onGetSurveyList(result: BaseMdl<List<SurveyResponseMdl>>) {
//    initData(result)
    }

    override fun displayData(t: List<SurveyMdl>) {
//        initData(t)
    }

    private lateinit var mPresenter: SurveyPresenter
    var listSurvey:MutableList<SurveyResponseMdl> = mutableListOf()
    var listMySurvey:MutableList<SurveyResponseMdl> = mutableListOf()

    fun startThisActivity(context: Activity){
        val intent= Intent(context, SurveyActivity::class.java)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            context.startActivity(intent,
//                    ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
//        }else{
//            context.startActivity(intent)
//        }
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.survey_activity)

        setSupportActionBar(toolbar)
        supportActionBar!!.title="Survey"
        mPresenter = SurveyPresenter(this)
        mPresenter.loadData()

        setupViewPager()
    }


//    private fun initData(result: List<SurveyMdl>?) {
//        for (i in result!!.indices){
//            if (result[i].type==1){
//                listSurvey.add(result[i])
//            }else{
//                listMySurvey.add(result[i])
//            }
//        }
//        setupViewPager()
//    }

    private fun setupViewPager() {
        tabLayoutSurvey.setupWithViewPager(viewPagerSurvey)
        val pagerAdapter= SurveyPagerAdapter(supportFragmentManager)


        val fragSurvey = SurveyContainerFragment().newsInstance(Gson().toJson(listSurvey))
        val fragMySurvey = MySurveyContainerFragment().newsInstance(Gson().toJson(listMySurvey))
        pagerAdapter.addFragment(fragSurvey,getString(R.string.survey_list))
        pagerAdapter.addFragment(fragMySurvey,getString(R.string.my_survey))
        viewPagerSurvey.adapter = pagerAdapter
    }
}
