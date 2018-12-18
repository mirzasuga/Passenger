package com.stickearn.stickpass.view.verify_otp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.stickearn.stickpass.R
import kotlinx.android.synthetic.main.verify_otp_activity.*
import android.util.Log.wtf
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.view.login.LoginActivity
import com.stickearn.stickpass.view.main.MainActivity
import org.jetbrains.anko.startActivity
import android.opengl.ETC1.getHeight
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.util.TypedValue
import android.util.DisplayMetrics
import android.widget.Toast
import com.google.gson.Gson
import com.stickearn.stickpass.model.*
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.utils.sms.SmsVerify
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.os.CountDownTimer
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.utils.getStringBefore
import com.stickearn.stickpass.utils.sms.OnSmsCatchListener




class VerifyOTPActivity : AppCompatActivity(),OtpView {
    override fun onSuccessNewCode(data: ResendOtpPinResponseMdl) {
        startCount()
    }

    override fun showLoading() {

    }

    override fun errorLoading(errorMessage: String?) {

        tvError.visibility = View.VISIBLE
        tvError.text = errorMessage
        secondPinView.setText("")
        secondPinView.isFocusable=true
      secondPinView.isInTouchMode

    }

    override fun stopLoading() {
    }

    override fun onSuccessValidate(data: OtpPinResponseMdl) {
        Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
        if (mUserMdl!=null){
            PrefHelper.saveAuth(this,mUserMdl)
            PrefHelper.saveName(this, Utils.getUserMdl(this).account.name!!.getStringBefore(" "))
            startActivity<MainActivity>()
            finish()
        }else{
            LoginActivity().startThisActivity(this)
            finish()
        }

    }

    var mUserMdl: LoginResponseMdl? =null

    var TAG_USER = "user"
    var TAG_REMEMBER = "remember"
    lateinit var user: AccountMdl
    lateinit var mPresenter: OtpPresenter
    lateinit var smsVerifyCatcher: SmsVerify
    lateinit var tvKet:TextView
    lateinit var tvResendCode:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verify_otp_activity)

        secondPinView.setAnimationEnable(true)
        mPresenter = OtpPresenter(this)
        tvKet=findViewById(R.id.tvKet)
        tvResendCode = findViewById(R.id.tvRecentCode)

        mUserMdl = intent.getSerializableExtra(TAG_USER) as LoginResponseMdl
        user = Utils.convertUser(this, mUserMdl!!.user!!)
//        wtf("user",Gson().toJson(mUserMdl))
        tvPhone.text = user.account.phone.toString()

        startCount()

        tvResendCode.setOnClickListener {
            val data= ResendOtpPinRequestMdl(user.account.phone)
            mPresenter.resendOtpCode(data)
        }
        //init SmsVerifyCatcher
        smsVerifyCatcher = SmsVerify(this, object : OnSmsCatchListener<String> {
            override fun onSmsCatch(message: String) {
                val code = smsVerifyCatcher.parseCode(message)//Parse verification code
               wtf("Message",code)
                secondPinView.setText(code)
                checkCode()
            }
        })
        btnSend.setOnClickListener {

            checkCode()
        }

        secondPinView.setOnClickListener{
            rl_otp.getViewTreeObserver().addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val heightDiff = rl_otp.getRootView().getHeight() - rl_otp.getHeight()
                    if (heightDiff > dpToPx(this@VerifyOTPActivity, 200F)) { // if more than 200 dp, it's probably a keyboard...
                        // ... do something here
                    }
                }
            })
        }
    }

    private fun startCount() {
        object : CountDownTimer(300000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tvKet.text = "${getString(R.string.not_receiving_sms)} ${millisUntilFinished / 1000}"
                tvRecentCode.visibility = View.GONE

            }

            override fun onFinish() {
                tvKet.text =  getString(R.string.not_receiving_sms)
                tvRecentCode.visibility = View.VISIBLE
            }
        }.start()
    }


    fun dpToPx(context: Context, valueInDp: Float): Float {
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
    }

    private fun checkCode() {
        val mCode = secondPinView.text.toString()
        wtf("mcode", mCode)

        val data = OtpPinRequestMdl(mCode,user.account.phone,user.account.email)
        mPresenter.validateOtp(data)

    }

    fun startThisActivity(context: Context, user: LoginResponseMdl) {
        val intent = Intent(context, VerifyOTPActivity::class.java)
        intent.putExtra(TAG_USER, user)
        context.startActivity(intent)
    }
    fun startThisActivity(context: Context) {
        val intent = Intent(context, VerifyOTPActivity::class.java)
        context.startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        if (smsVerifyCatcher!=null){
            smsVerifyCatcher.onStart()
        }


    }

    override fun onStop() {
        super.onStop()
        if (smsVerifyCatcher!=null){
            smsVerifyCatcher.onStop()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



}