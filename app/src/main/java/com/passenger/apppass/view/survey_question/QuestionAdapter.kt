package com.stickearn.stickpass.view.survey_question

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.model.ChatMdl
import android.view.animation.AnimationUtils


/**
 * Created by oohyugi on 2/14/18.
 */
class QuestionAdapter(private val context : Context, private val list : List<ChatMdl>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val TYPE_QUESTION=1
    private val TYPE_ANSWER=2
    private val TYPE_LOADING=3
    private var isLoading: Boolean = false
    private var visibleThreshold = 5
    private var lastVisibleItem: Int = 0
    val totalItemCount: Int = 0
    private var onLoadMoreListener: OnLoadMoreListener? = null
    var lastPosition:Int = 0

    init {
        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            if (onLoadMoreListener != null) {
                onLoadMoreListener!!.onLoadMore()
            }
            isLoading = true
            lastPosition = list.size-1
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        val context = parent!!.context
        val inflater = LayoutInflater.from(context)

        when (viewType) {
            TYPE_QUESTION -> {
                // self message
                val view = inflater.inflate(R.layout.item_question, parent, false)
                return QuestionViewHolder(view)
            }
            TYPE_ANSWER -> {
                val view = inflater.inflate(R.layout.item_answer, parent, false)

                return AnswerHolder(view)
            }
            TYPE_LOADING -> {
                val view = inflater.inflate(R.layout.item_loading, parent, false)
                return LoadingHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.item_question, parent, false)
                return QuestionViewHolder(view)
            }
        }



    }

     private inner class AnswerHolder( itemView: View?): RecyclerView.ViewHolder(itemView) {
         internal var tvText:TextView = itemView!!.findViewById(R.id.message)


     }

   private inner class QuestionViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
       internal var tvText:TextView = itemView!!.findViewById(R.id.message)


    }
   inner class LoadingHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
//       internal var progressBar: ProgressBar? = itemView!!.findViewById(R.id.progressBar)

    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun getItemViewType(position: Int): Int {
        if (list[position].type === 1) {
            return TYPE_QUESTION
        } else if (list[position].type === 2) {
            return TYPE_ANSWER
        }
        else if (list[position].type === 3) {
            return TYPE_LOADING
        }
        return TYPE_ANSWER

    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = list[position]
        if (holder is QuestionViewHolder) {
            initDataQuestion(holder, item)
        } else if (holder is AnswerHolder) {
            initDataAnswer(holder, item)
        } else if (holder is LoadingHolder) {
            initDataLoading(holder, item)
        }

        setAnimation(holder!!.itemView, position)


    }

    private fun setAnimation(itemView: View?, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
            itemView!!.startAnimation(animation)
            lastPosition = position
        }
    }

    private fun initDataLoading(holder: LoadingHolder, item: ChatMdl) {

    }

    private fun initDataAnswer(holder: AnswerHolder, item: ChatMdl) {

        holder.tvText.text=item.questionText
    }

    private fun initDataQuestion(holder: QuestionViewHolder, item: ChatMdl) {

        holder.tvText.text=item.questionText
    }


    fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    fun setLoaded() {
        isLoading = false
    }

}


