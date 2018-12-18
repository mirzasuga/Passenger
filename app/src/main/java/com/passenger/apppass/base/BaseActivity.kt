package com.stickearn.stickpass.base

import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.stickearn.stickpass.R
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.facebook.appevents.AppEventsLogger


/**
 * Created by oohyugi on 1/15/18.
 */
abstract class BaseActivity: AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        toolbar= findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId ==android.R.id.home){
            supportFinishAfterTransition()
//            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        DisposableManager().dispose()
        super.onDestroy()
    }


    fun startProgress(){



    }
    fun stopProgress(){


    }
    fun ErrorProgress(view: View,message: String){
        Snackbar.make(view,message,Snackbar.LENGTH_LONG).show()
    }

}