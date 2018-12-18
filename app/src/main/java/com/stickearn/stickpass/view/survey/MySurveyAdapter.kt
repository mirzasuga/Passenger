package com.stickearn.stickpass.view.survey

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.model.SurveyMdl
import com.stickearn.stickpass.model.SurveyResponseMdl
import com.stickearn.stickpass.utils.loadImageWithPicassoTransformation

/**
 * Created by macos-vanya on 06/03/18.
 */
class MySurveyAdapter (private val context : Context, private val list : List<SurveyResponseMdl>): RecyclerView.Adapter<MySurveyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName: TextView
        var tvDate: TextView
        var tvPoint: TextView
        var imgType: ImageView

        init {

            tvName = itemView.findViewById(R.id.tv_survey_history)
            tvDate = itemView.findViewById(R.id.tv_date_survey_history)
            imgType = itemView.findViewById(R.id.iv_survey_history)
            tvPoint = itemView.findViewById(R.id.tv_point_survey_history)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):ViewHolder {
        val view : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_survey_history, parent, false)

        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item: SurveyResponseMdl = list[position]
        holder.tvName.text = item.title
        holder.tvDate.text = "${item.createdAt}"
        holder.tvPoint.text = "${item.point.toString()}"
        holder.imgType.loadImageWithPicassoTransformation(item.imageUrl.toString())
    }
}