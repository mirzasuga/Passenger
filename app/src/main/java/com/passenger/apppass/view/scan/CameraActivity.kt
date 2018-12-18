package com.stickearn.stickpass.view.scan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stickearn.stickpass.R
import com.stickearn.stickpass.view.main.MainActivity
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.util.Log
import kotlinx.android.synthetic.main.scan_activity.*
import me.dm7.barcodescanner.zbar.ZBarScannerView


class CameraActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private var mScannerView: ZBarScannerView? = null

    //camera permission is needed.

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZBarScannerView(this)    // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun handleResult(result: me.dm7.barcodescanner.zbar.Result) {
        // Do something with the result here
        Log.v("kkkk", result.contents) // Prints scan results
        Log.v("uuuu", result.barcodeFormat.name) // Prints the scan format (qrcode, pdf417 etc.)

//        ScanActivity().updateText(result.contents)
//        et_code.setText(result.contents)
//        onBackPressed()
        val intent = Intent()
        intent.putExtra("result",result.contents)

        setResult(1,intent)
        onBackPressed()

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}
