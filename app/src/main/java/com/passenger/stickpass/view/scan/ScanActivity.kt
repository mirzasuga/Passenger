package com.stickearn.stickpass.view.scan

import android.Manifest
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stickearn.stickpass.R
import com.stickearn.stickpass.view.mart.MartActivity
import kotlinx.android.synthetic.main.scan_activity.*
import com.stickearn.stickpass.helper.StringHelper
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.Log.wtf
import android.view.View
import com.google.gson.Gson
import com.stickearn.stickpass.model.MartMdl
import com.stickearn.stickpass.utils.Utils
import mbanje.kurt.fabbutton.FabButton


/**
 * Created by macos-vanya on 19/01/18.
 */
class ScanActivity : AppCompatActivity(),ScanView {
    override fun showLoading() {

        progress_dialog.visibility=View.VISIBLE
        fab_scan.visibility=View.GONE
    }

    override fun errorLoading(errorMessage: String?) {
//        progress_dialog.visibility=View.GONE
//        fab_scan.visibility=View.VISIBLE
        if (tvErrorCode.visibility ==View.GONE){
            tvErrorCode.visibility =View.VISIBLE
            tvErrorCode.text = errorMessage
        }

//        fabButton.showProgress(false)

    }

    override fun stopLoading() {
//        fabButton.showProgress(false)
        progress_dialog.visibility=View.GONE
        fab_scan.visibility=View.VISIBLE

    }

    override fun onLoadedMartSuccess(data: List<MartMdl>) {
        martList.addAll(data)
//        for (response in data){
//            martList.add(MartMdl(response.pricePoint,response.updatedAt,response.productId,response.createdAt,response.martBoxId,response.category,response.productDescription,response.stock,response.productName,response.priceCurrency,response.status))
//        }

        MartActivity().startThisActivity(this,et_code.text.toString(),Gson().toJson(martList))
    }

    fun startThisActivity(context: Activity){
        val intent=Intent(context,ScanActivity::class.java)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            context.startActivity(intent,
//                    ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
//        }else{
//            context.startActivity(intent)
//        }
        context.startActivity(intent)
    }


    var martList:MutableList<MartMdl> = mutableListOf()
    lateinit var mPresenter: ScanPresenter
    lateinit var fabButton :FabButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_activity)
        mPresenter = ScanPresenter(this)
//        fabButton=findViewById(R.id.determinate)
        requestPermission()

        fab_scan.setOnClickListener {
            if (StringHelper.checkETStatus(this,et_code)) {
                if (tvErrorCode.visibility ==View.VISIBLE){
                    tvErrorCode.visibility =View.GONE
                }
                mPresenter.martLoadData(et_code.text.toString(), Utils.getAuth(this),Utils.getToken(this))

            }else{
                StringHelper.checkETStatus(this,et_code)
            }
        }

        ib_scan.setOnClickListener {
           val intent = Intent(this,CameraActivity::class.java)
            startActivityForResult(intent,1)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==1){
            if (data!=null) {
                val kode = data!!.getStringExtra("result")
                wtf("kode", kode)
                et_code.setText(kode)
            }
        }
    }

    fun updateText(scanCode:String) {
        et_code.setText(scanCode);
    }

    fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.size < 1) {
            requestPermission()
        }
    }
}