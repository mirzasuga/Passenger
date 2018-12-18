package com.stickearn.stickpass.view.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.wtf
import com.stickearn.stickpass.R
import com.stickearn.stickpass.view.main.MainActivity
import com.stickearn.stickpass.view.register.RegisterActivity
import org.jetbrains.anko.startActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.stickearn.stickpass.view.complete_account.CompleteAccountActivity
import com.stickearn.stickpass.view.forgot_pass.ForgotPassActivity
import com.facebook.*
import com.facebook.login.LoginManager
import java.util.*
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.login_activity.*
import org.json.JSONObject
import android.app.Activity
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.gson.Gson
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.LoginResponseMdl
import com.google.android.gms.common.api.ApiException
import com.stickearn.stickpass.model.LoginRequestMdl
import com.stickearn.stickpass.model.RegisterRequestMdl
import com.stickearn.stickpass.utils.getStringBefore


class LoginActivity : AppCompatActivity(), LoginView {
    override fun errorLoadingSosmed(erorrMessage: String, mUser: RegisterRequestMdl) {

        if (erorrMessage.equals("User not registered")){

            CompleteAccountActivity().startThisActivity(this@LoginActivity,mUser)
            finish()
        }else{
            Toast.makeText(this,erorrMessage,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAuthSuccessSosmed(data: LoginResponseMdl, remember: LoginRequestMdl) {
        PrefHelper.saveAuth(this,data)
        PrefHelper.saveRemember(this,remember)
        val data64 =StringHelper.fromBase64(data.user!!)
        val account= StringHelper.convertStringUserToJson(data64!!)
        PrefHelper.saveName(this, account!!.account.name!!.getStringBefore(" "))
        startActivity<MainActivity>()
        finish()
    }

    override fun showLoading() {

        progressDialog.visibility=View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
    }

    override fun stopLoading() {
        progressDialog.visibility=View.GONE
    }

    override fun onAuthSuccess(data: LoginResponseMdl, remember: LoginRequestMdl) {

        PrefHelper.saveAuth(this,data)
        PrefHelper.saveRemember(this,remember)
        val data64 =StringHelper.fromBase64(data.user!!)
        val account= StringHelper.convertStringUserToJson(data64!!)
        PrefHelper.saveName(this, account!!.account.name!!.getStringBefore(" "))
        startActivity<MainActivity>()
        finish()
    }

    var mGoogleApiClient: GoogleApiClient? = null
    var callbackManager: CallbackManager? = null
    private val RC_SIGN_IN = 11
    var permissionNeeds = Arrays.asList("public_profile, email, user_birthday")
    lateinit var progressDialog:LinearLayout
    lateinit var presenter:LoginPresenter
    lateinit var mEmail:String
    lateinit var mPass:String
    lateinit var loginViewActivity:RelativeLayout
    lateinit var loginRequestMdl: LoginRequestMdl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        configurasiGoogleSign()
        progressDialog = findViewById(R.id.progress_dialog)
        callbackManager = CallbackManager.Factory.create()
        presenter = LoginPresenter(this)

        loginViewActivity = findViewById(R.id.loginViewActivity)
        lgBtnLogin.setOnClickListener({
            mEmail = lgetPhoneNumber.text.toString()
            mPass = lgPass.text.toString()
            loginRequestMdl = LoginRequestMdl(phone = "62$mEmail",password = mPass,fcm_token = PrefHelper.getFcmToken(this))
            presenter.postAuth(loginRequestMdl)
//                startActivity<MainActivity>()
//                finish()





        })
        lgbtnRegister.setOnClickListener {

            startActivity<RegisterActivity>()
        }
        btnGoogle.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }
        tvForgotPass.setOnClickListener {
            startActivity<ForgotPassActivity>()
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

    var userID: String? = null
    var birthday: String? = null
    lateinit var picture: String
    lateinit var name: String
    var email: String? = null

    private fun facebookUser(token: AccessToken?) {
         val request:GraphRequest = GraphRequest.newMeRequest(
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
//                            val token =`object`.getString("")
//                            val mUser = RegisterRequestMdl(name = name,email = email,provider_id = userID,provider = "facebook")
//                            CompleteAccountActivity().startThisActivity(this@LoginActivity,mUser)
//                            finish()
                            mUser = RegisterRequestMdl(name = name,email = email,provider_id = userID,provider = "facebook",fcm_token = PrefHelper.getFcmToken(this@LoginActivity))
                            loginRequestMdl = LoginRequestMdl(provider = "facebook",providerID = userID,fcm_token = PrefHelper.getFcmToken(this@LoginActivity))
                            presenter.postAuthSosmed(loginRequestMdl,mUser)

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
                .enableAutoManage(this) { connectionResult -> wtf( "onConnectionFailed: ", connectionResult.errorMessage!!) }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

    }

    fun startThisActivity(context: Activity) {
        val intent = Intent(context, LoginActivity::class.java)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            context.startActivity(intent,
//                    ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
//        }else{
//            context.startActivity(intent)
//        }
        context.startActivity(intent)
    }
    lateinit var mUser: RegisterRequestMdl
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                wtf( "onActivityResult: ", Gson().toJson(account))

                mUser = RegisterRequestMdl(name = account.displayName,email = account.email,provider_id = account.id,provider = "google", fcm_token = PrefHelper.getFcmToken(this))
                loginRequestMdl = LoginRequestMdl(provider = "google",providerID = account.id,fcm_token = PrefHelper.getFcmToken(this))
                presenter.postAuthSosmed(loginRequestMdl,mUser)
//                CompleteAccountActivity().startThisActivity(this,mUser)
//                finish()

            }catch (e:Exception){
                e.printStackTrace()
            }

        }

    }

    override fun onBackPressed() {
        startActivity<MainActivity>()
        finish()
    }
}
