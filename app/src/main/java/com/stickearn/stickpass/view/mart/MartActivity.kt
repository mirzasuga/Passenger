package com.stickearn.stickpass.view.mart


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.stickearn.stickpass.view.mart.MartAdapter.OnItemClickListener
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.util.Log.wtf
import android.view.View.GONE
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.Gson
import com.stickearn.stickpass.R
import com.stickearn.stickpass.base.BaseActivity
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.*
import com.stickearn.stickpass.utils.Utils
import com.stickearn.stickpass.view.mart_detail.MartDetailActivity
import com.stickearn.stickpass.view.order_complete.OrderCompleteActivity
import kotlinx.android.synthetic.main.mart_activity.*


/**
 * Created by macos-vanya on 22/01/18.
 */
class MartActivity : BaseActivity(), OnItemClickListener, CartAdapter.OnCartItemClickListener,MartView {

    //when user click image show detail item
    override fun onClickItem(position: Int, item: MartMdl) {
        MartDetailActivity().startThisActivity(this,item.productId.toString(),martId)
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

    override fun onCheckoutSuccess(data: CheckoutResponseMdl) {
        Log.d("CheckoutResponseMdl",data.toString())
        OrderCompleteActivity().startThisActivity(this,data)
    }

    override fun onCartItemClickPlus(position: Int, data: CartMdl, qty: Int, totalPrice: Int, totalPoint: Int) {
        isClickMart =false
        martMdlList.clear()
        mMartMdl = MartMdl(productId = data.productId, imgUrl = data.imgUrl, productName = data.productName, priceCurrency = data.priceCurrency,pricePoint = data.pricePoint ,qty = qty,totalPrice = totalPrice, totalPoint = totalPoint,stock = data.stock)
        mapMart[data.productId] = mMartMdl

        var price:Int=0
        var point:Int=0


        for (i in cartMdlList.indices) {
            wtf("data cart",Gson().toJson(cartMdlList[i]) )

            price += cartMdlList[i].totalPrice
            point += cartMdlList[i].totalPoint
        }

        changeQtyMart(mapMart, qty, position)

        cartMdlList.clear()
        mcartMdl = CartMdl(productId = data.productId, imgUrl = data.imgUrl, productName = data.productName, priceCurrency = data.priceCurrency,pricePoint = data.pricePoint ,qty = qty,totalPrice = totalPrice, totalPoint = totalPoint,stock = data.stock)
        mapCart[data.productId] = mcartMdl
        insertItemToCart(mapCart, qty, position)
    }


    override fun onCartItemClickMinus(position: Int, data: CartMdl, qty: Int, totalPrice: Int, totalPoint: Int) {
        isClickMart =false
        martMdlList.clear()
        mMartMdl = MartMdl(productId = data.productId, imgUrl = data.imgUrl, productName = data.productName, priceCurrency = data.priceCurrency,pricePoint = data.pricePoint ,qty = qty,totalPrice = totalPrice, totalPoint = totalPoint,stock = data.stock)
        mapMart[data.productId] = mMartMdl
        changeQtyMart(mapMart, qty, position)

        cartMdlList.clear()
        mcartMdl = CartMdl(productId = data.productId, imgUrl = data.imgUrl, productName = data.productName, priceCurrency = data.priceCurrency,pricePoint = data.pricePoint ,qty = qty,totalPrice = totalPrice, totalPoint = totalPoint,stock = data.stock)
        mapCart[data.productId] = mcartMdl

        insertItemToCart(mapCart, qty, position)
    }

    var isClickMart:Boolean = false
    override fun onItemClickPlus(position: Int, data: MartMdl, qty: Int, totalPrice: Int, totalPoint: Int) {
        isClickMart =true
        mBottomSheetBehavior!!.peekHeight = ly_payment.height
        wtf("height",ly_payment.height.toString())
        ly_item.setPadding(0, 0, 0, ly_payment.height)
        cartMdlList.clear()
        mcartMdl = CartMdl(productId = data.productId, imgUrl = data.imgUrl, productName = data.productName, priceCurrency = data.priceCurrency,pricePoint = data.pricePoint ,qty = qty,totalPrice = totalPrice, totalPoint = totalPoint,stock = data.stock)

        mapCart[data.productId] = mcartMdl

        insertItemToCart(mapCart, qty, position)
        wtf("mart", data.productId.toString())
        martMdlList.clear()
        mMartMdl = MartMdl(productId = data.productId, imgUrl = data.imgUrl, productName = data.productName, priceCurrency = data.priceCurrency,pricePoint = data.pricePoint ,qty = qty,totalPrice = totalPrice, totalPoint = totalPoint,stock = data.stock)
        mapMart[data.productId] = mMartMdl
        changeQtyMart(mapMart,qty,position)

    }


    override fun onItemClickMinus(position: Int, data: MartMdl, qty: Int, totalPrice: Int, totalPoint: Int) {
        isClickMart =true
        cartMdlList.clear()
        mcartMdl = CartMdl(productId = data.productId, imgUrl = data.imgUrl, productName = data.productName, priceCurrency = data.priceCurrency,pricePoint = data.pricePoint ,qty = qty,totalPrice = totalPrice, totalPoint = totalPoint,stock = data.stock)

        mapCart[data.productId] = mcartMdl

        insertItemToCart(mapCart, qty, position)
        wtf("mart", data.productId.toString())
        martMdlList.clear()
        mMartMdl = MartMdl(productId = data.productId, imgUrl = data.imgUrl, productName = data.productName, priceCurrency = data.priceCurrency,pricePoint = data.pricePoint ,qty = qty,totalPrice = totalPrice, totalPoint = totalPoint,stock = data.stock)
        mapMart[data.productId] = mMartMdl
        changeQtyMart(mapMart,qty,position)
    }


    var cartMdlList = mutableListOf<CartMdl>()
    var martMdlList = mutableListOf<MartMdl>()
    private var mcartMdl: CartMdl = CartMdl()
    private var mMartMdl: MartMdl = MartMdl()
    var mapCart: HashMap<Int, CartMdl> = HashMap<Int, CartMdl>()
    var mapMart: HashMap<Int, MartMdl> = HashMap<Int, MartMdl>()
    var isFirstLoadData:Boolean = false


    /**
     *
     * after button plus clicked, remove data, and insert new data to cart
     * @param data
     */
    private fun insertItemToCart(data: HashMap<Int, CartMdl>, qty: Int, position: Int) {


        for (item in data.entries) {
            mcartMdl = CartMdl(productId = item.value.productId, imgUrl = item.value.imgUrl, productName = item.value.productName, priceCurrency = item.value.priceCurrency,pricePoint = item.value.pricePoint ,qty = item.value.qty, totalPrice = item.value.totalPrice, totalPoint = item.value.totalPoint,stock = item.value.stock)
            if (item.value.qty==0){
                cartMdlList.remove(mcartMdl)
            }else{
                cartMdlList.add(mcartMdl)
            }
        }

        var price:Int=0
        var point:Int=0

        for (i in cartMdlList.indices) {
            wtf("data cart",Gson().toJson(cartMdlList[i]) )

            price += cartMdlList[i].totalPrice
            point += cartMdlList[i].totalPoint

            tvTotalPriceCurrency.text = "${ StringHelper.indonesiaFormat(price.toDouble())}"
            tvPoint.text = "${point}pts"
        }

        tvQtyCart.visibility = View.VISIBLE
        tvQtyCart.text = cartMdlList.size.toString()


        mCartAdapter.notifyDataSetChanged()

        if (cartMdlList.size<1){
            mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
            mBottomSheetBehavior!!.peekHeight = 0
            ly_item.setPadding(0, 0, 0, 0)
        }
    }

    var totalPoint:Int =0
    var totalCurrency:Int=0

    private fun changeQtyMart(data: HashMap<Int, MartMdl>, qty: Int, position: Int) {
        for (item in data) {
            mMartMdl = MartMdl(productId = item.value.productId, imgUrl = item.value.imgUrl, productName = item.value.productName, priceCurrency = item.value.priceCurrency,pricePoint = item.value.pricePoint ,qty = item.value.qty, totalPrice = item.value.totalPrice, totalPoint = item.value.totalPoint,stock = item.value.stock)
//            martMdlList.add(mMartMdl)
            if (item.value.qty==0 && !isFirstLoadData){
                martMdlList.remove(mMartMdl)
            }else{
                martMdlList.add(mMartMdl)
            }

        }

        var price:Int=0
        var point:Int=0

        for (i in cartMdlList.indices) {
            wtf("data cart",Gson().toJson(cartMdlList[i]) )
            price += cartMdlList[i].totalPrice
            point += cartMdlList[i].totalPoint
        }
        totalPoint = point
        totalCurrency = price
        tvTotalPriceCurrency.text = "${ StringHelper.indonesiaFormat(price.toDouble())}"
        tvPoint.text = "${point}pts"

        adapter.notifyDataSetChanged()
//        initMart(position)
        wtf("data Mart", Gson().toJson(martMdlList))
    }

    fun startThisActivity(context:Context,martId:String,martList:String){
        val intent = Intent(context,MartActivity::class.java)
        intent.putExtra(TAG_ID,martId)
        intent.putExtra(TAG_MART_LIST,martList)
        context.startActivity(intent)

    }
    private lateinit var adapter: MartAdapter


    private var mBottomSheetBehavior: BottomSheetBehavior<LinearLayout>? = null
    lateinit var mCartAdapter: CartAdapter
    lateinit var martId:String
    var TAG_ID = "martID"
    var TAG_MART_LIST = "martList"
    var paymentMethod:String="point"
    var martList:List<MartMdl> = mutableListOf()
    lateinit var progressDialog:LinearLayout
    lateinit var mPresenter:MartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mart_activity)
        martId = intent.getStringExtra(TAG_ID)
        mPresenter = MartPresenter(this)
        progressDialog = findViewById(R.id.progress_dialog)
//        observeLiveData()
        isFirstLoadData=true

        martList = StringHelper.getListFromJsonString(intent.getStringExtra(TAG_MART_LIST))


        wtf("mart",Gson().toJson(martList))

        initFisrtData()

//        changeQtyMart(mapMart, 0, 0)

        setupBottomsheet()

        wtf("cart data", Gson().toJson(mapMart))
        mCartAdapter = CartAdapter(this, cartMdlList, this)
        rv_cart.adapter = mCartAdapter
        rv_cart.layoutManager = LinearLayoutManager(this)

        view_touch.setOnClickListener {
            mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        supportActionBar!!.title ="StickMart"


        rb_point.isChecked = true
        rb_point.setOnClickListener {
            if (rb_cash.isChecked)
                rb_point.isChecked = true
            rb_cash.isChecked = false
                paymentMethod = "point"
            }

        rb_cash.setOnClickListener {
            if (rb_point.isChecked)
                rb_cash.isChecked = true
            rb_point.isChecked = false
                paymentMethod="cash"
            }

        ib_checkout.setOnClickListener{
            initCheckoutData()
//            startActivity<WaitingDriverActivity>()


        }

        iv_cart.setOnClickListener{
            if (view_touch.visibility == GONE) {
                mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
            }else{
                mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun initFisrtData() {
        for (i in martList.indices) {
            val martMdl:MartMdl  = martList[i]
            mapMart[martList[i].productId] = martMdl
        }

        for (item in mapMart) {
            mMartMdl = MartMdl(productId = item.value.productId, imgUrl = item.value.imgUrl, productName = item.value.productName, priceCurrency = item.value.priceCurrency,pricePoint = item.value.pricePoint ,qty = item.value.qty, totalPrice = item.value.totalPrice, totalPoint = item.value.totalPoint, stock = item.value.stock)
//            martMdlList.add(mMartMdl)
            if (item.value.qty==0 && !isFirstLoadData){
                martMdlList.remove(mMartMdl)
            }else{
                martMdlList.add(mMartMdl)
            }

        }
        initMart(0)
    }

    lateinit var rvMartState: Parcelable
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        rvMartState = rv_mart.layoutManager.onSaveInstanceState()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
       rv_mart.layoutManager.onRestoreInstanceState(rvMartState)

    }
    private fun initCheckoutData() {
//        val totalPriceCurrency = tvTotalPriceCurrency.text.toString().toInt()
//        val totalPoint = tvPoint.text.toString().toInt()
        val martItemList:MutableList<MartItemMdl> = cartMdlList
                .map { MartItemMdl(it.totalPrice, it.productId, it.qty, it.pricePoint) }
                .toMutableList()

        val checkoutData = CheckoutRequestMdl(grandTotalPoint = totalPoint,martItem = martItemList,grandTotalPrice = totalCurrency,martIdDisplay = martId,paymentMethod = paymentMethod )

        val baseCheckOut = PayloadRequestBaseMdl(checkoutData)

        mPresenter.postCheckout(Utils.getAuth(this),Utils.getToken(this),baseCheckOut)
        wtf("checkout data",Gson().toJson(baseCheckOut))
    }



    private fun initMart(position: Int) {

        val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        adapter = MartAdapter(this, martMdlList, this)
        rv_mart.adapter = adapter
        rv_mart.layoutManager = layoutManager
        /*val dividerItemDecoration = DividerItemDecoration(this,
                GridLayoutManager.HORIZONTAL)
        val dividerItemDecorationVertical = DividerItemDecoration(this,
                GridLayoutManager.VERTICAL)
        rv_mart.addItemDecoration(dividerItemDecoration)
        rv_mart.addItemDecoration(dividerItemDecorationVertical)*/
//        if (isClickMart){
//            adapter.notifyItemRangeChanged(0, adapter.itemCount)
//            adapter.notifyItemChanged(position)
//        }else{
            adapter.notifyDataSetChanged()
//        }
    }

    private fun setupBottomsheet() {
        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        mBottomSheetBehavior!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> finish()
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        view_touch.visibility = GONE
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

                view_touch.visibility = View.VISIBLE
                view_touch.alpha = slideOffset

            }
        })
    }
}