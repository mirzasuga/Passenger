package com.stickearn.stickpass.view.main

/**
 * Created by macos-vanya on 18/01/18.
 */

import android.app.Activity
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.MenuMdl
import com.stickearn.stickpass.view.login.LoginActivity
import com.stickearn.stickpass.view.scan.ScanActivity
import com.stickearn.stickpass.view.survey.SurveyActivity
import com.stickearn.stickpass.view.webview.WebviewActivity

class MenuAdapter(val context : Activity, private val list : List<MenuMdl>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleTextView: TextView
        var thumbImageView : ImageView
        var cv_menu : CardView

        init {
            titleTextView = itemView.findViewById(R.id.tv_menu)
            thumbImageView = itemView.findViewById(R.id.iv_menu)
            cv_menu = itemView.findViewById(R.id.cv_menu)
//            itemView.setOnClickListener(this)
        }
    }
    override fun onCreateViewHolder(parent : ViewGroup, type : Int) : MenuAdapter.ViewHolder{
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder : MenuAdapter.ViewHolder, position : Int){
        var album : MenuMdl = list.get(position)
        holder.titleTextView.text = album.name
        holder.thumbImageView.setImageResource(album.thumbnail)
        holder.itemView.setOnClickListener{
            when (position) {
                0 -> SurveyActivity().startThisActivity(context)
                1 -> {
                    if (PrefHelper.getAuth(context)!=null){
                        ScanActivity().startThisActivity(context)
                    }else{
                        LoginActivity().startThisActivity(context)
                    }


                }
                2 -> WebviewActivity().startThisActivity(context!!,"FAQ",4)
                3 -> WebviewActivity().startThisActivity(context!!,"Privacy Policy",1)
            }
        }
    }

    override fun getItemCount() : Int{
        return list.size
    }

}
