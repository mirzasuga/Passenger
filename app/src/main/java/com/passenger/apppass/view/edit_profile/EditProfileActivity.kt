package com.stickearn.stickpass.view.edit_profile

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log.wtf
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.helper.FragmentHelper
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.EdiProfileRequestMdl
import com.stickearn.stickpass.model.ProfileResponseMdl
import com.stickearn.stickpass.model.UserMdl
import com.stickearn.stickpass.utils.RxBus
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.utils.getStringAfter
import com.stickearn.stickpass.utils.getStringBefore
import com.stickearn.stickpass.view.account.AccountFragment
import com.stickearn.stickpass.view.main.MainActivity
import kotlinx.android.synthetic.main.edit_profile_activity.*

class EditProfileActivity : BaseActivity(),EditProfileView {
    override fun showLoading() {
        progressDialog.visibility = View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
    }

    override fun stopLoading() {
        progressDialog.visibility = View.GONE
    }

    override fun onEditProfileSuccess(result: ProfileResponseMdl) {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
//        onBackPressed()
        PrefHelper.saveName(this,result.firstName)
        RxBus.INSTANCE.send("Hello World!")

    }

    lateinit var mPresenter:EditProfilePresenter
    val TAG_USER ="user"
    lateinit var firstName:String
    lateinit var lastName:String
    lateinit var progressDialog: LinearLayout
    fun startThisActivity(context: Context,mUser:ProfileResponseMdl){
        val intent = Intent(context,EditProfileActivity::class.java)
        intent.putExtra(TAG_USER,mUser)
        context.startActivity(intent)
    }

    lateinit var profileMdl:ProfileResponseMdl
    var mName:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)
        progressDialog = findViewById(R.id.progress_dialog)
        mPresenter = EditProfilePresenter(this)
        profileMdl = intent.getSerializableExtra(TAG_USER) as ProfileResponseMdl
        if (profileMdl.name!=null){
            mName = profileMdl.name!!

        }else{
            mName = "${profileMdl.firstName} ${profileMdl.lastName}"

        }

        etEmail.setText(profileMdl.email)
        etName.setText(mName)
        etPhoneNumber.setText(profileMdl.phone)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.action_save ->{
                    mName = etName.text.toString()
                    firstName = mName.getStringBefore(" ")
                    lastName = mName.getStringAfter(" ")
                    wtf("name","first $firstName, last $lastName")
                    val data = EdiProfileRequestMdl(phone = profileMdl.phone,firstName =firstName,lastName = lastName,email = profileMdl.email,uuid = Utils.getUuidUser(this) )
                    mPresenter.postEditProfile(Utils.getToken(this),Utils.getAuth(this),data)
                }
                android.R.id.home->
                        onBackPressed()
            }
        }
        return true
    }
}
