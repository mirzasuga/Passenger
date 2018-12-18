package com.stickearn.stickpass.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stickearn.stickpass.R
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.stickearn.stickpass.model.DataSurveyAnswerMdl


/**
 * Created by oohyugi on 2/14/18.
 */
class ImageChoiceAdapter(private val context: Context, private val list: List<DataSurveyAnswerMdl>, val multipleSelectect: ImageSelected, val subType: Int) : RecyclerView.Adapter<ImageChoiceAdapter.ViewHolder>() {


    var itemSelected: MutableList<DataSurveyAnswerMdl> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val context = parent!!.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_picture_choice, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        internal var tvText: TextView = itemView!!.findViewById(R.id.tvTitle)
        internal var img: ImageView = itemView!!.findViewById(R.id.img)
        internal var lyChoice: LinearLayout = itemView!!.findViewById(R.id.ly_choice)
        internal var lyClicklable: LinearLayout = itemView!!.findViewById(R.id.lyClicklable)
        internal var lyContainer: RelativeLayout = itemView!!.findViewById(R.id.ly_container)

    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ImageChoiceAdapter.ViewHolder?, position: Int) {
        val item = list[position]
        holder!!.tvText.text = item.answerTitle
        holder.img.loadImageWithPicasso(item.answerImage!!)
        if (position == 0) {
            holder.lyContainer.setMargins(45, 0, 4, 0)
//            Utils().setMargins(holder!!.lyChoice,45,0,0,0)
        } else {
            holder.lyContainer.setMargins(0, 0, 4, 0)
//            Utils().setMargins(holder!!.lyChoice,0,0,0,0)
        }
        if (subType == 1) {
            holder.lyChoice.setOnClickListener {
                itemSelected.add(item)
                multipleSelectect.onSelectedImage(itemSelected, subType)
            }
        } else if (subType == 2) {
            holder.lyChoice.setOnClickListener {
                if (!item.clicked!!) {
                    item.clicked = true

//                holder.lyChoice.setBackgroundResource(R.drawable.shape_rounded_gray)
                    itemSelected.add(item)
                    notifyDataSetChanged()
                    multipleSelectect.onSelectedImage(itemSelected, subType)
                } else {
                    item.clicked = false
//                  holder.lyChoice.setBackgroundResource(R.drawable.shape_rounded_gray)
                    itemSelected.remove(item)
                    notifyDataSetChanged()
                    multipleSelectect.onSelectedImage(itemSelected, subType = subType)
                }

            }

            if (item.clicked!!) {

                holder.lyClicklable.visibility = View.GONE

            } else {
                holder.lyClicklable.visibility = View.VISIBLE
            }

        }


    }


    interface ImageSelected {
        fun onSelectedImage(itemSelected: MutableList<DataSurveyAnswerMdl>, subType: Int)
    }


}


