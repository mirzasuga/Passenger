package com.stickearn.stickpass.view.complete_account

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.*
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.verify_otp.VerifyOTPActivity
import kotlinx.android.synthetic.main.complete_account_activity.*
import org.jetbrains.anko.toast

class CompleteAccountActivity : AppCompatActivity(),CompleteAccountView {
    override fun showLoading() {
            mProgressBar.visibility = View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
      Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()

    }

    override fun stopLoading() {
        mProgressBar.visibility = View.GONE
    }

    override fun onRegisterSuccess(t: BaseMdl<LoginResponseMdl>, registerRequestMdl: RegisterRequestMdl) {

        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
        val data= LoginRequestMdl(provider = registerRequestMdl.provider, providerID = registerRequestMdl.provider_id )
        PrefHelper.saveRemember(this, data)
        VerifyOTPActivity().startThisActivity(this,t.data)
        finish()
    }

    lateinit var mUserMdl: RegisterRequestMdl
    var TAG_USER="user"
    lateinit var mEmail:String
    lateinit var mName:String
    lateinit var mPhoneNumber:String
   var mPass:String = ""
    lateinit var mConfirmPass:String
    lateinit var mPresenter:CompleteAccountPresenter
    lateinit var mProgressBar: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.complete_account_activity)
        mUserMdl = intent.getSerializableExtra(TAG_USER) as RegisterRequestMdl
        etEmail.setText(mUserMdl.email)
        etName.setText(mUserMdl.name)
        mProgressBar = findViewById(R.id.progress_dialog)
        etPhoneNumber.requestFocus()
        etPhoneNumber.isFocusable=true

        mPresenter = CompleteAccountPresenter(this)
        btnNext.setOnClickListener {
            mEmail = etEmail.text.toString()
            mName = etName.text.toString()
            mPhoneNumber = etPhoneNumber.text.toString()

            if (validateInput()){
                mPass = "${mUserMdl.provider_id!!}${DateHelper.getTimeStamp()}"
                val mPassRandom = StringHelper.getRandomString(mPass)
                Log.wtf("mPass", mPassRandom)
                val registerRequestMdl = RegisterRequestMdl(email = mEmail, name = mName, phone =  "62$mPhoneNumber", password = mPassRandom, passwordConfirmation = mPassRandom,provider =mUserMdl.provider,provider_id = mUserMdl.provider_id )

//                VerifyOTPActivity().startThisActivity(this,user)
                mPresenter.postRegister(registerRequestMdl)
//                finish()
            }
        }

    }

    private fun validateInput(): Boolean {
        var isValidate:Boolean =true

        if (etPhoneNumber.text.length<8){
            isValidate=false
            toast("Phone to short")
        }

        return isValidate
    }

    fun startThisActivity(context: Context, userMdl: RegisterRequestMdl) {
        val intent = Intent(context, CompleteAccountActivity::class.java)
        intent.putExtra(TAG_USER, userMdl)
        context.startActivity(intent)
    }
}
