package com.stickearn.stickpass.view.survey


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.wtf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.SurveyMdl
import com.stickearn.stickpass.model.SurveyResponseMdl
import com.stickearn.stickpass.model.VoucherMdl
import com.stickearn.stickpass.utils.Utils
import kotlinx.android.synthetic.main.survey_container_fragment.*


/**
 * A simple [Fragment] subclass.
 */
class SurveyContainerFragment : BaseFragment(), SurveyView {
    override fun showLoading() {
        mProgressBar.visibility= View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
    }

    override fun stopLoading() {
        mProgressBar.visibility= View.GONE
    }

    override fun displayData(t: List<SurveyMdl>) {

    }

    override fun onGetSurveyList(result: BaseMdl<List<SurveyResponseMdl>>) {
        listSurvey.addAll(result.data)
        adapter.notifyDataSetChanged()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.survey_container_fragment, container, false)
    }

    var TAG_DATA:String="data"
    fun newsInstance( history: String): SurveyContainerFragment {
        val args= Bundle()
        args.putString(TAG_DATA,history)
        val fragment = SurveyContainerFragment()
        fragment.arguments=args
        return fragment
    }



    lateinit var mPresenter: SurveyPresenter
    lateinit var adapter: SurveyAdapter
    lateinit var mProgressBar: LinearLayout
    var listSurvey:MutableList<SurveyResponseMdl> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mProgressBar = view.findViewById(R.id.progress_dialog)
        adapter = SurveyAdapter(activity!!, listSurvey)
        val data = arguments!!.getString(TAG_DATA)
        wtf("data",data)
        rv_survey.layoutManager = LinearLayoutManager(this!!.activity)
//        val adapter = SurveyAdapter(activity!!,convertStringTolist(data))
        rv_survey.adapter = adapter
        adapter.notifyDataSetChanged()
        mPresenter = SurveyPresenter(this)
        mPresenter.getSurveyList(Utils.getAuth(this.activity!!),
                Utils.getToken(this.activity!!))
    }

    fun convertStringTolist(string: String):List<SurveyResponseMdl>{
        val listType = object : TypeToken<List<SurveyResponseMdl>>() {}.type


        val list = Gson().fromJson<List<SurveyResponseMdl>>(string,listType)
        return list

    }

}// Required empty public constructor
