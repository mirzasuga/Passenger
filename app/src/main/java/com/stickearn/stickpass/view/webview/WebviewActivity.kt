package com.stickearn.stickpass.view.webview

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.wtf
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.model.PolicyMdl
import com.stickearn.stickpass.utils.Utils
import kotlinx.android.synthetic.main.webview_activity.*
import okhttp3.internal.Util

class WebviewActivity : BaseActivity(),WebviewView {
    override fun showLoading() {
        progressDialog.visibility= View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
    }

    override fun stopLoading() {
        progressDialog.visibility=View.GONE
    }

    override fun displayPolicy(result: PolicyMdl) {
        showData(result)

    }

    private fun showData(result: PolicyMdl) {
        webview.settings.javaScriptEnabled=true
        webview.webViewClient = WebViewClient()
        wtf("body",result.body)
        webview.loadData(result.body,"text/html; charset=UTF-8",null)

    }


    lateinit var mTitle: String
    lateinit var mWeburl: String
    var mType: Int=0
    lateinit var progressDialog: LinearLayout
    lateinit var mPresenter: WebviewPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_activity)
        progressDialog =findViewById(R.id.progress_dialog)
        mTitle = intent.getStringExtra(TAG_TITLE)
        mType = intent.getIntExtra(TAG_TYPE,0)

        mPresenter = WebviewPresenter(this)
//        if (mTitle.contains("Privacy")){
//            mPresenter.loadPolicy(Utils.getAuth(this),Utils.getToken(this))
//        }
        mPresenter.loadPolicy(Utils.getAuth(this),Utils.getToken(this),mType)
//        webview.settings.javaScriptEnabled=true
//        webview.webViewClient = WebViewClient()
//        webview.loadUrl(mWeburl)
        supportActionBar!!.title=mTitle

    }

    public var TAG_TITLE: String = "title"
    public var TAG_WEBURL: String = "url"
    public var TAG_TYPE: String = "type"
    fun startThisActivity(context: Activity, title: String, type:Int) {
        val intent = Intent(context, WebviewActivity::class.java)

        intent.putExtra(TAG_TITLE, title)
        intent.putExtra(TAG_TYPE, type)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            context.startActivity(intent,
//                    ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
//        }else{
//            context.startActivity(intent)
//        }
        context.startActivity(intent)
    }
}
