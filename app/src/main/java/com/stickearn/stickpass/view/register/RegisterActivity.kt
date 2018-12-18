package com.stickearn.stickpass.view.register

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.*
import com.stickearn.stickpass.view.complete_account.CompleteAccountActivity
import com.stickearn.stickpass.view.login.LoginActivity
import com.stickearn.stickpass.view.verify_otp.VerifyOTPActivity

import kotlinx.android.synthetic.main.register_activity.*
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import java.util.*


class RegisterActivity : AppCompatActivity(),RegisterView {
    override fun errorLoadingSosmed(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }

    override fun onCheckemailSosmedSuccess(t: BaseMdl<LoginResponseMdl>, account: String) {

    }

    override fun showLoading() {

        progressDialog.visibility = View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
    }

    override fun stopLoading() {
        progressDialog.visibility = View.GONE
    }

    override fun onRegisterSuccess(t: BaseMdl<LoginResponseMdl>, mRembember: RegisterRequestMdl) {

        Toast.makeText(this, t.message,Toast.LENGTH_LONG).show()

        val data= LoginRequestMdl(phone =mRembember.phone, password = mRembember.passwordConfirmation )
        PrefHelper.saveRemember(this, data)
        VerifyOTPActivity().startThisActivity(this, t.data)
        finish()
    }




    lateinit var mUserMdl: UserMdl
    var TAG_USER="user"
    var mGoogleApiClient: GoogleApiClient? = null
    var callbackManager: CallbackManager? = null
    private val RC_SIGN_IN = 11
    var permissionNeeds = Arrays.asList("public_profile, email, user_birthday")
    lateinit var mPresenter:RegisterPresenter
    lateinit var progressDialog:LinearLayout
    lateinit var mEmail:String
    lateinit var mName:String
    lateinit var mPhoneNumber:String
    lateinit var mPass:String
    lateinit var mConfirmPass:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        mPresenter = RegisterPresenter(this)
        callbackManager = CallbackManager.Factory.create()
        progressDialog = findViewById(R.id.progress_dialog)
        configurasiGoogleSign()

        rgbtnLogin.setOnClickListener({
            startActivity<LoginActivity>()
        })

        rgbtnRegister.setOnClickListener {
            mName = rgetName.text.toString()
            mEmail = rgetEmail.text.toString()
            mPhoneNumber = rgetPhoneNumber.text.toString()
            mPass = rgPass.text.toString()
            mConfirmPass = rgConfirmPass.text.toString()
            if (validateRegister(mName,mEmail,mPhoneNumber,mPass,mConfirmPass)){
//                val user = UserMdl(email = rgetEmail.text.toString(),name = rgetName.text.toString(),phone_number = rgetPhoneNumber.text.toString())
                val registerRequestMdl = RegisterRequestMdl(email = mEmail,name = mName,phone = "62$mPhoneNumber", password = mPass,passwordConfirmation = mConfirmPass,fcm_token = PrefHelper.getFcmToken(this))
//
                mPresenter.postRegister(registerRequestMdl)
//
            }
        }
        btnGoogle.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }

        btnFacebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds)
            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    facebookUser(loginResult.accessToken)


                }

                override fun onCancel() {
                    Log.e("cancel", "onCancel: ")
                    val token = AccessToken.getCurrentAccessToken()
                    if (token != null) {
                        facebookUser(token)
                    } else {

                    }
//                    Log.e("cancel", "onCancel: " + token!!.userId)

                }

                override fun onError(error: FacebookException) {
                    Log.e("cancel", "onError: " + error.message)

                }
            })

        }

    }

    private fun validateRegister(mName: String, mEmail: String, mPhoneNumber: String, mPass: String, mConfirmPass: String): Boolean {
        var isValidate =true
        if (!StringHelper.isEmailValid(mEmail)){
            isValidate=false
            rgetEmail.error = getString(R.string.invalid_format)

        }
        if (mPhoneNumber.length<8){
            isValidate=false
            rgetPhoneNumber.error = getString(R.string.phone_short)
        }
        if (TextUtils.isEmpty(mName)){
            isValidate=false
            rgetName.error = getString(R.string.field_empty)
        }
        if (TextUtils.isEmpty(mPass)){
            isValidate=false
            rgPass.error = getString(R.string.field_empty)
        }else if (!TextUtils.isEmpty(mPass) && mPass.length <6){
            isValidate=false
            rgPass.error = getString(R.string.pass_short)
        }
        if (TextUtils.isEmpty(mConfirmPass)){
            isValidate=false
            rgConfirmPass.error = getString(R.string.field_empty)
        }else if (!TextUtils.isEmpty(mConfirmPass) && mPass.length <6){
            isValidate=false
            rgConfirmPass.error = getString(R.string.pass_short)
        }

        if (!mConfirmPass.equals(mPass)){
            isValidate=false
            rgConfirmPass.error = getString(R.string.wrong_confrm_pass)
        }

        return isValidate

    }

    var userID: String? = null
    var birthday: String? = null
    lateinit var picture: String
    lateinit var name: String
    var email: String? = null

    private fun facebookUser(token: AccessToken?) {
        val request: GraphRequest = GraphRequest.newMeRequest(
                token,
                object : GraphRequest.GraphJSONObjectCallback {
                    override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {
                        Log.e("LoginActivity", response.toString())

                        // Application code
                        try {
                            userID = `object`!!.getString("id")
//                            birthday = `object`.getString("birthday") // 01/31/1980 format
                            picture = "https://graph.facebook.com/$userID/picture?type=large"
                            name = `object`.getString("name")
                            email = `object`.getString("email")
                            val mUser =RegisterRequestMdl(name = name,email = email,provider = "facebook",provider_id = userID)
                            CompleteAccountActivity().startThisActivity(this@RegisterActivity,mUser)
                            finish()

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                })

        val parameters= Bundle()
        parameters.putString("fields", "id,name,email,gender,birthday,picture")
        request.parameters = parameters
        request.executeAsync()





    }
    private fun configurasiGoogleSign() {
        //CONFIGURASI GOOGLE LOGIN
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this) { connectionResult -> Log.wtf("onConnectionFailed: ", connectionResult.errorMessage!!) }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                Log.wtf("onActivityResult: ", account.id )

                val mUser = RegisterRequestMdl(name = account.displayName,email = account.email,provider_id = account.id,provider = "google", password = account.id,passwordConfirmation = account.id)
                mPresenter.postCheckEmailSosmed(mUser,account.id)


            }catch (e:Exception){
                e.printStackTrace()
            }

        }

    }


    fun startThisActivity(context: Context, userMdl: UserMdl) {
        val intent = Intent(context, RegisterActivity::class.java)
        intent.putExtra(TAG_USER, userMdl)
        context.startActivity(intent)
    }

}
