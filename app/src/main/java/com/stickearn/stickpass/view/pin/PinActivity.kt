package com.stickearn.stickpass.view.pin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.view.main.MainActivity
import com.stickearn.stickpass.view.verify_otp.VerifyOTPActivity
import kotlinx.android.synthetic.main.activity_pin.*
import org.jetbrains.anko.startActivity

class PinActivity : AppCompatActivity() {

    val pin:String = ""
    private var mListPin: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        setPin()
    }

    /**
     * this method used to save the digit clicked to list
     */
    private fun setPin() {

        mListPin = ArrayList()

        frameLayout_number1.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("1")
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number2.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("2")
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number3.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("3")
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number4.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("4")
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number5.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("5")
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number6.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("6")
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number7.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("7")
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number8.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("8")
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number9.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("9")
                //Toast.makeText(InputPinActivity.this, "Size : "+ mListPin!!.size, Toast.LENGTH_LONG).show();
                conditioningPinButton()
            } else {

            }
        })

        frameLayout_number0.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size <= 3) {
                mListPin!!.add("0")
                conditioningPinButton()
            } else {

            }
        })

        /**
         * Delete pin one by one, after that, change the background of indicator
         */
        frameLayout_deletePin.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size !== 0) {
                mListPin!!.removeAt(mListPin!!.size - 1)
                if (mListPin!!.size === 3) {
                    textView_pin4.setBackgroundResource(R.drawable.nonselected_item)
                } else if (mListPin!!.size === 2) {
                    textView_pin3.setBackgroundResource(R.drawable.nonselected_item)
                } else if (mListPin!!.size === 1) {
                    textView_pin2.setBackgroundResource(R.drawable.nonselected_item)
                } else if (mListPin!!.size === 0) {
                    textView_pin1.setBackgroundResource(R.drawable.nonselected_item)
                }
            }
        })

        /*frameLayout_next.setOnClickListener(View.OnClickListener {
            if (mListPin!!.size < 4) {
                Toast.makeText(this@AuthActivity, R.string.msg_complete_your_pin, Toast.LENGTH_LONG).show()
                return@OnClickListener
            }
//            Toast.makeText(this@AuthActivity, R.string.msg_open_your_next_activity, Toast.LENGTH_LONG).show()

            //print your PIN
            var yourPin = ""
            for (i in 0 until mListPin!!.size) {
                yourPin += mListPin!!.get(i)
            }
            mPresenter.martLoadData(yourPin)
        })*/
    }

    /**
     * this method used to set the background of indicator pin when has filled by a digit
     */
    private fun conditioningPinButton() {
        if (mListPin!!.size === 1) {
            textView_pin1.setBackgroundResource(R.drawable.selected_item)
        } else if (mListPin!!.size === 2) {
            textView_pin2.setBackgroundResource(R.drawable.selected_item)
        } else if (mListPin!!.size === 3) {
            textView_pin3.setBackgroundResource(R.drawable.selected_item)
        } else if (mListPin!!.size === 4) {
            textView_pin4.setBackgroundResource(R.drawable.selected_item)

            var yourPin = ""
            for (i in 0 until mListPin!!.size) {
                yourPin += mListPin!!.get(i)
            }
            startActivity<MainActivity>()
            finish()
        }
    }
}
