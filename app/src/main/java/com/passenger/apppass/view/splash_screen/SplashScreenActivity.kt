package com.stickearn.stickpass.view.splash_screen

import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log.wtf
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.LoginRequestMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.main.MainActivity
import com.stickearn.stickpass.view.onboarding.OnboardingActivity
import org.jetbrains.anko.startActivity
import java.util.*

/**
 * Created by oohyugi on 1/30/18.
 */
class SplashScreenActivity:AppCompatActivity(),SplashView {
    override fun showLoading() {

    }

    override fun errorLoading(errorMessage: String?) {
    }

    override fun stopLoading() {
    }

    override fun onAuthSuccess(data: LoginResponseMdl) {
            PrefHelper.saveAuth(this,data)
        startActivity<MainActivity>()
            finish()
    }

    lateinit var mPresenter:SplashPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isFirst:Boolean = PrefHelper.getIsFirstInstall(this)!!

        val android_id : String = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        val language : String = Locale.getDefault().getDisplayLanguage().toString()

        wtf("android id",android_id)
        wtf("language",language)
        mPresenter = SplashPresenter(this)

        if (isFirst){
//
            startActivity<OnboardingActivity>()
            finish()
        }else{
            checkExpiredToken()

        }
//        else{
//            startActivity<MainActivity>()
//            finish()
//        }
    }

lateinit var loginRequestMdl: LoginRequestMdl
    fun checkExpiredToken() {
        if (PrefHelper.getAuth(this)!=null){
            val mExpiredTime = DateHelper.beforeAfterDate(DateHelper.getDateNow(DateHelper.DATE_INPUT_v1), PrefHelper.getAuth(this)!!.expiredAt!!)
            wtf("expire",mExpiredTime.toString())
            if (mExpiredTime){
                if (PrefHelper.getRemember(this)!!.password!=null && PrefHelper.getRemember(this)!!.phone!=null){
                    loginRequestMdl = LoginRequestMdl(phone = PrefHelper.getRemember(this)!!.phone,password = PrefHelper.getRemember(this)!!.password )
                }else{
                    loginRequestMdl =LoginRequestMdl(provider = PrefHelper.getRemember(this)!!.provider,providerID =  PrefHelper.getRemember(this)!!.providerID )
                }
                mPresenter.postAuth(loginRequestMdl)
            }else{
                startActivity<MainActivity>()
                finish()
            }
        }else{
            startActivity<MainActivity>()
            finish()
        }

    }

}