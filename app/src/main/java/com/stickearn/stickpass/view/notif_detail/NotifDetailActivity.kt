package com.stickearn.stickpass.view.notif_detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.Toast
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.model.NotificationResponseMdl
import com.stickearn.stickpass.utils.RxBus
import com.stickearn.stickpass.utils.Utils
import kotlinx.android.synthetic.main.notif_detail_activity.*

class NotifDetailActivity : BaseActivity(),NotifDetailView {
    override fun showLoading() {
        progressDialog.visibility = View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG)
    }

    override fun stopLoading() {
        progressDialog.visibility = View.GONE
    }

    override fun displayDetail(data: NotificationResponseMdl) {
        webview.settings.javaScriptEnabled=true
        webview.webViewClient = WebViewClient()
        webview.loadData(data.body,"text/html; charset=UTF-8",null)
// Publish events to mainactivity for check user read the messafe
        RxBus.INSTANCE.send("Hello World!")
    }

    lateinit var progressDialog: LinearLayout
    lateinit var mPresenter : NotifDetailPresenter
    var mId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notif_detail_activity)
        supportActionBar!!.title = "Detail"
        progressDialog = findViewById(R.id.progress_dialog)
        mId = intent.getIntExtra(TAG_ID,0)
        mPresenter = NotifDetailPresenter(this)
        mPresenter.getDetail(Utils.getAuth(this),Utils.getToken(this),mId)

    }

    val TAG_ID = "id"
    fun startThisActivity(context: Context,id:Int){
        val intent  = Intent(context,NotifDetailActivity::class.java)
        intent.putExtra(TAG_ID,id)
        context.startActivity(intent)
    }



}
