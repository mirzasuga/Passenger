package com.stickearn.stickpass.view.waiting_driver

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.stickearn.stickpass.R
import android.content.Intent
import com.stickearn.stickpass.view.order_complete.OrderCompleteActivity
import com.stickearn.stickpass.view.order_failed.OrderFailedActivity
import kotlinx.android.synthetic.main.waiting_driver_activity.*


class WaitingDriverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waiting_driver_activity)
        Handler().postDelayed({ startActivity(Intent(this, OrderCompleteActivity::class.java))
        finish()}, 3000)

        btn_cancel_waiting.setOnClickListener{
            onBackPressed()
            finish()
        }
    }
}
