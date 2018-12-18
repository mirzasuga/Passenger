package com.stickearn.stickpass.view.survey

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log.wtf
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.model.SurveyMdl
import com.stickearn.stickpass.model.SurveyResponseMdl
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformation
import com.stickearn.stickpass.view.survey_detail.SurveyDetailActivity

/**
 * Created by macos-vanya on 08/02/18.
 */
class SurveyAdapter(private val context : Context, private val list : List<SurveyResponseMdl>): RecyclerView.Adapter<SurveyAdapter.ViewHolder>() {


    var isMoreLoading = false

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*val id:Int?=0,
          val name:String?=null,
          val description:String?=null,
          val point:Int?=0,
          val date:String?=null,
          val thumbnail : Int*/

        var tvName: TextView
        var tvPoint: TextView
        var imgType: ImageView

        init {

            tvName = itemView.findViewById(R.id.tv_survey_title)
            tvPoint = itemView.findViewById(R.id.tv_point_survey)
            imgType = itemView.findViewById(R.id.iv_survey_list)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ViewHolder {
        val view : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_list_survey, parent, false)

        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: SurveyResponseMdl = list[position]
        wtf("item",item.uuid)
        wtf("item",item.toString())
        holder.tvName.text = item.title
        holder.tvPoint.text = "${item.point.toString()}"
        holder.imgType.loadImageWithPicassoTransformation(item.imageUrl.toString())

        holder.itemView.setOnClickListener {
            SurveyDetailActivity().startThisActivity(context,item.uuid)
//            (context as AppCompatActivity).finish()
        }
    }
}