package com.stickearn.stickpass.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stickearn.stickpass.R
import android.widget.LinearLayout
import com.stickearn.stickpass.model.DataSurveyAnswerMdl


/**
 * Created by oohyugi on 2/14/18.
 */
class MultipleChoiceAdapter(private val context: Context, private val list: List<DataSurveyAnswerMdl>, val multipleSelectect: MultipleSelected, val subType: Int) : RecyclerView.Adapter<MultipleChoiceAdapter.ViewHolder>() {


    var itemSelected:MutableList<DataSurveyAnswerMdl> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val context = parent!!.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_multiple_choice, parent, false)
        return ViewHolder(view)
    }

     inner class ViewHolder( itemView: View?): RecyclerView.ViewHolder(itemView) {
        internal var tvText:TextView = itemView!!.findViewById(R.id.tvName)
        internal var lyChoice:LinearLayout = itemView!!.findViewById(R.id.ly_choice)
        internal var lyContainer:LinearLayout = itemView!!.findViewById(R.id.ly_container)
     }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder:MultipleChoiceAdapter.ViewHolder?, position: Int) {
        val item = list[position]
        if (position==0){
            holder!!.lyContainer.setMargins(45,0,4,0)
//            Utils().setMargins(holder!!.lyChoice,45,0,0,0)
        }else{
            holder!!.lyContainer.setMargins(0,0,4,0)
//            Utils().setMargins(holder!!.lyChoice,0,0,0,0)
        }
        holder.tvText.text=item.answerTitle
        if (subType ==1){ //single check
            holder.lyChoice.setOnClickListener {
                itemSelected.add(item)
                multipleSelectect.onSelected(itemSelected,subType)
            }
        }else if (subType==2){ //multiple check
            holder.lyChoice.setOnClickListener {
                if (!item.clicked){
                    item.clicked=true

//                holder.lyChoice.setBackgroundResource(R.drawable.shape_rounded_gray)
                    itemSelected.add(item)
                    multipleSelectect.onSelected(itemSelected,subType)
                    notifyDataSetChanged()
                }else{
                    item.clicked=false
//                holder.lyChoice.setBackgroundResource(R.drawable.shape_rounded_gray)
                    itemSelected.remove(item)
                    multipleSelectect.onSelected(itemSelected,subType)
                    notifyDataSetChanged()
                }

            }
            if (item.clicked){

                holder.lyChoice.setBackgroundResource(R.drawable.shape_rounded_primary)

            }else{
                holder.lyChoice.setBackgroundResource(R.drawable.shape_rounded_dark_gray)
            }
        }
    }


    interface MultipleSelected{
        fun onSelected(itemSelected: MutableList<DataSurveyAnswerMdl>, subType: Int)
    }
}


