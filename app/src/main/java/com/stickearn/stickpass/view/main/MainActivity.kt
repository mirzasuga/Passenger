package com.stickearn.stickpass.view.main

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import android.util.Log
import android.util.Log.wtf
import android.view.Menu
import android.view.MenuItem
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.FragmentHelper
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.view.home.HomeFragment

import kotlinx.android.synthetic.main.activity_main.*
import com.stickearn.stickpass.view.account.AccountFragment
import com.stickearn.stickpass.view.history.HistoryFragment
import com.stickearn.stickpass.view.login.LoginActivity
import com.stickearn.stickpass.view.notification.NotificationFragment
import android.widget.Toast
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView
import android.view.Gravity
import com.stickearn.stickpass.base.DisposableManager
import com.stickearn.stickpass.helper.DateHelper
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.PointResponseMdl
import com.stickearn.stickpass.utils.RxBus
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.splash_screen.SplashScreenActivity


class MainActivity : AppCompatActivity(), MainView {


    override fun showLoading() {

    }

    override fun errorLoading(errorMessage: String?) {
    }

    override fun stopLoading() {
    }

    override fun onSuccessLoadUnReadNotif(mListUnReadNotif: Int) {


        if (mListUnReadNotif > 0) {
            addBadgeAt(TAG_NOTIF_MENU, mListUnReadNotif)
        }
    }

    private var mSelectedItem = 0
    private val SELECTED_ITEM = "selected_item"
    lateinit var qBadgeView: QBadgeView
    var TAG_NOTIF_MENU = 2
    lateinit var mPresenter: MainPresenter
    private var disableItemId = false
    var isClickBack =0
    lateinit var menuHome:MenuItem
    lateinit var menuHistory:MenuItem
    lateinit var menuNotif:MenuItem
    lateinit var menuAccount:MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = MainPresenter(this)
        if (PrefHelper.getAuth(this) != null) {
            mPresenter.getNotificationCount(Utils.getAuth(this), Utils.getToken(this))
            // Listen for Bolean events only from detail notification

        }

        wtf("TOKEN",PrefHelper.getFcmToken(this))

        RxBus.INSTANCE.toObserverable().subscribe({
            mPresenter.getNotificationCount(Utils.getAuth(this), Utils.getToken(this))
        })

        qBadgeView = QBadgeView(this)
        bottom_navigation.enableAnimation(false)
        bottom_navigation.enableShiftingMode(false)
        bottom_navigation.enableItemShiftingMode(false)
        initBottomNavigasi(savedInstanceState)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SELECTED_ITEM, mSelectedItem)
        Log.e("onSaveInstanceState: ", mSelectedItem.toString())
        super.onSaveInstanceState(outState)
    }


    fun initBottomNavigasi(savedInstanceState: Bundle?) {


        bottom_navigation.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            selectedFragment(item)

            true
        }

        val selectedItem: MenuItem

        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0)
            selectedItem = bottom_navigation.getMenu().findItem(mSelectedItem)


        } else {
            selectedItem = bottom_navigation.getMenu().getItem(0)
        }
        selectedFragment(selectedItem)

    }

    private fun selectedFragment(item: MenuItem) {
        var frag: Fragment? = null
        if (bottom_navigation!=null){
            menuHome =  bottom_navigation.menu.getItem(0)
            menuHistory =bottom_navigation.menu.getItem(1)
            menuNotif = bottom_navigation.menu.getItem(2)
            menuAccount =bottom_navigation.menu.getItem(3)
        }


        when (item.itemId) {
            R.id.action_home -> {

                frag = HomeFragment().newsInstance()
                FragmentHelper.replaceFragment(this, frag, R.id.container)
                    menuHome.isEnabled = false

               menuHistory.isEnabled = true
                menuNotif.isEnabled = true
               menuAccount.isEnabled = true

            }


            R.id.action_account -> {
                if (PrefHelper.getAuth(this) != null) {
                    frag = AccountFragment().newsInstance()
                  menuHome.isEnabled = true
                   menuHistory.isEnabled = true
                    menuNotif.isEnabled = true
                    menuAccount.isEnabled = false
                    FragmentHelper.replaceFragment(this, frag, R.id.container)


                } else {
                    LoginActivity().startThisActivity(this)
                    finish()
                }

            }

            R.id.action_notif -> {
                frag = NotificationFragment().newsInstance()
                menuHome.isEnabled = true

                menuHistory.isEnabled = true
                menuNotif.isEnabled = false
               menuAccount.isEnabled = true
                FragmentHelper.replaceFragment(this, frag, R.id.container)


            }


            R.id.action_history -> {
                frag = HistoryFragment().newsInstance()
                menuHome.isEnabled = true

                menuHistory.isEnabled = false
                menuNotif.isEnabled = true
                menuAccount.isEnabled = true
               FragmentHelper.replaceFragment(this, frag, R.id.container)
            }


        }


        Log.e("selectedFragment: ", item.itemId.toString() + "")
        mSelectedItem = item.itemId


    }

    fun gotoAccount() {
       menuAccount.isChecked = true
        menuHome.isEnabled = true
        menuHistory.isEnabled = true
        menuNotif.isEnabled = true
        menuAccount.isEnabled = false
        FragmentHelper.replaceFragment(this, AccountFragment().newsInstance(), R.id.container)
    }

    fun gotoHome() {
      menuHome.isChecked = true
        menuHome.isEnabled = false
        menuHistory.isEnabled = true
        menuNotif.isEnabled = true
        menuAccount.isEnabled = true
        FragmentHelper.replaceFragment(this, HomeFragment().newsInstance(), R.id.container)
    }


    fun addBadgeAt(position: Int, number: Int): Badge {
        // add badge
        qBadgeView = QBadgeView(this)
        qBadgeView.setBadgeNumber(number)
                .setGravityOffset(18F, 2F, true)
                .setBadgeGravity(Gravity.END or Gravity.TOP)
                .bindTarget(bottom_navigation.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener { dragState, badge, targetView ->
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                        Toast.makeText(this@MainActivity, "remove", Toast.LENGTH_SHORT).show()
                }

        return qBadgeView

    }



    override fun onBackPressed() {

        initBackPress()



    }

    private fun initBackPress() {
        if (mSelectedItem ==menuHome.itemId){
            if (isClickBack==0){

                isClickBack =1
                Toast.makeText(this,getString(R.string.exit_app),Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    isClickBack =0
                },1000)
            }else{
                finishAffinity()
            }
        }else{
                selectedFragment(menuHome)
            menuHome.isChecked = true
        }
    }

    override fun onResume() {
        super.onResume()
//        SplashScreenActivity().checkExpiredToken()
    }


}
