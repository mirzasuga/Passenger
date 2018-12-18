package com.stickearn.stickpass.view.account

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseFragment
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.model.PointResponseMdl
import com.stickearn.stickpass.model.ProfileResponseMdl
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.utils.inflate
import com.stickearn.stickpass.view.edit_profile.EditProfileActivity
import com.stickearn.stickpass.view.main.MainActivity
import com.stickearn.stickpass.view.webview.WebviewActivity
import kotlinx.android.synthetic.main.account_fragment.*
import com.stickearn.stickpass.model.UserMdl
import com.stickearn.stickpass.utils.RxBus
import com.stickearn.stickpass.view.login.LoginActivity


/**
 * Created by macos-vanya on 23/01/18.
 */

class AccountFragment: BaseFragment(),AccountView{
    override fun onSuccessLoadProfile(result: ProfileResponseMdl) {
        initDataProfile(result)
    }



    override fun showLoadingPoint() {
        progressPoint.visibility = View.VISIBLE
        tvPoint.visibility = View.GONE
    }

    override fun stopLoadingPoint() {
        progressPoint.visibility = View.GONE
        tvPoint.visibility = View.VISIBLE
    }

    override fun errorLoadingPoint(errorMessage: String) {
        tvPoint.visibility = View.VISIBLE
        if (isAdded && mContext!=null){
            if (errorMessage.equals("The item is null")){
                tvPoint.text = "$mPoint pts"
            }else{
                tvPoint.text = getString(R.string.retry)
                tvPoint.setTextColor(ContextCompat.getColor(this.context!!,R.color.colorRed))
                tvPoint.isClickable = true
                tvPoint.setOnClickListener {
                    callApiPoint()
                }
            }

        }

    }



    override fun onSuccessLoadMyPoint(result: PointResponseMdl) {
        initDataPoint(result)
    }

    override fun showLoading() {

        progressDialog.visibility = View.VISIBLE
    }

    override fun errorLoading(errorMessage: String?) {
        Utils.showSnackBar(accountView, errorMessage!!,false)

    }

    override fun stopLoading() {
        progressDialog.visibility = View.GONE
    }





    fun newsInstance() : AccountFragment {
        val bundle=Bundle()
        val fragment= AccountFragment()

        fragment.arguments=bundle

        return fragment
    }

    lateinit var progressPoint: ProgressBar
    lateinit var progressDialog: LinearLayout
    lateinit var tvPoint: TextView
    lateinit var tvName:TextView
    lateinit var tvEmail:TextView
    lateinit var tvPhone:TextView
    lateinit var tvEdit:TextView
    lateinit var accountView: RelativeLayout
    lateinit var mPresenter: AccountPresenter
    var mPoint = 0
//    lateinit var mContext: FragmentActivity
//
//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context is Activity) {
//            this.mContext = context as FragmentActivity
//        }
//    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container!!.inflate(R.layout.account_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressPoint = view.findViewById(R.id.progressPoint)
        progressDialog = view.findViewById(R.id.progress_dialog)
        tvPoint = view.findViewById(R.id.tvPoint)
        tvName = view.findViewById(R.id.tvName)
        tvEmail = view.findViewById(R.id.tvEmail)
        tvPhone = view.findViewById(R.id.tvPhone)
        tvEdit = view.findViewById(R.id.tv_edit)
        accountView = view.findViewById(R.id.accountView)
        mPresenter = AccountPresenter(this)


        RxBus.INSTANCE.toObserverable().subscribe({
            mPresenter.getProfile(Utils.getAuth(mContext), Utils.getToken(mContext))
        })

        tvTermAndCondition.setOnClickListener {
            WebviewActivity().startThisActivity(mContext,"Term and Condition",2)
        }
        tvPrivacy.setOnClickListener {
            WebviewActivity().startThisActivity(mContext,"Privacy Policy",1)
        }
        tvLogout.setOnClickListener {
            PrefHelper.saveAuth(mContext,null)
            val activity = mContext as MainActivity?
            activity!!.gotoHome()
        }

        checkUser()
    }

    private fun callApiPoint() {
        mPresenter.getMyPoint(Utils.getAuth(mContext),
                Utils.getToken(mContext),
                Utils.getUuidUser(mContext))
    }
    private fun initDataPoint(result: PointResponseMdl) {
        tvPoint.setTextColor(ContextCompat.getColor(mContext,R.color.colorWhite))
        tvPoint.isClickable = false
        tvPoint.text = "${result.balance} pts"
        mPoint = result.balance


    }

     var mName:String=""
    private fun initDataProfile(result: ProfileResponseMdl) {

        mName = result.name ?: "${result.firstName} ${result.lastName}"

        tvName.text = mName.capitalize()
        tvEmail.text = result.email
        tvPhone.text = result.phone
        tvEdit.setOnClickListener({
            EditProfileActivity().startThisActivity(mContext,result)
        })
    }
    private fun checkUser() {
        if (PrefHelper.getAuth(mContext)!=null){
            val mUser = Utils.getUserMdl(mContext).account
            if (mUser!=null){
                ly_account.visibility=View.VISIBLE
                ly_login.visibility=View.GONE
//            tvName.text = mUser.name
//            tvEmail.text = mUser.email
//            tvPhone.text = mUser.phone
                callApiPoint()
                mPresenter.getProfile(Utils.getAuth(mContext),Utils.getToken(mContext))
            }else{
//            ly_account.visibility=View.GONE
//            ly_login.visibility=View.VISIBLE
                LoginActivity().startThisActivity(mContext)
                activity!!.finish()
            }
        }else{
            LoginActivity().startThisActivity(mContext)
            activity!!.finish()
        }

    }


}