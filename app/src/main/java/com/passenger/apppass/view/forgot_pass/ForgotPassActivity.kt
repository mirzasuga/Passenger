package com.stickearn.stickpass.view.forgot_pass

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.view.verify_otp.VerifyOTPActivity
import kotlinx.android.synthetic.main.forgot_pass_activity.*
import kotlinx.android.synthetic.main.register_activity.*
import org.jetbrains.anko.toast

class ForgotPassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_pass_activity)

        btnSend.setOnClickListener {
            if (inputValidate()){
                VerifyOTPActivity().startThisActivity(this)
            }
        }

    }

    private fun inputValidate(): Boolean {
        var isValidate:Boolean =true
        if (!StringHelper.isEmailValid(etEmail.text.toString())){
            isValidate=false
            toast("email error")
        }
        if (etPhoneNumber.text.length<8){
            isValidate=false
            toast("phone to short")
        }

        return isValidate
    }
}
