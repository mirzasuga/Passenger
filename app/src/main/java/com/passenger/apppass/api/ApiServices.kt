package com.stickearn.stickpass.api

import com.stickearn.stickpass.model.*
import io.reactivex.Observable
import retrofit2.http.*


/**
* Created by oohyugi on 1/15/18.
*/
interface ApiServices {

    @GET("kontrak")
    fun getDataExample(): Observable<List<ExampleMdl>>

    @FormUrlEncoded
    @POST("test-get-bank")
    fun getBank(@Field("driver_id") driverId: Int): Observable<BaseMdl<List<BankMdl>>>



    @GET("mart/box/{id}/item")
    fun getMart(@Header("Authorization") token:String ,@Header("StickHeader") auth:String,@Path("id")id:String):Observable<BaseMdl<List<MartResponseMdl>>>


    @GET("mart/box/{box_id}/item/{product_id}")
    fun getMartDetail(@Header("Authorization") token:String ,@Header("StickHeader") auth:String,@Path("box_id")boxId:String,@Path("product_id")productId:String):Observable<BaseMdl<MartResponseMdl>>


    @GET("order/history/{uuid_mart}?include=items")
    fun getMartHistory(@Header("Authorization") token:String ,@Header("StickHeader") auth:String,@Path("uuid_mart")uuidMart:String):Observable<BaseMdl<List<MartHistoryResponseMdl>>>


    @GET("question/{id}")
    fun getQuestion(@Path("id")id:Int):Observable<BaseMdl<QuestionMdl>>

    @POST("register")
    fun postRegister(@Body registerRequestMdl: RegisterRequestMdl):Observable<BaseMdl<LoginResponseMdl>>


    @POST("auth")
    fun postAuth(@Body loginRequestMdl: LoginRequestMdl):Observable<BaseMdl<LoginResponseMdl>>


    @FormUrlEncoded
    @POST("authentication/check")
    fun checkAuth(@Field("email")email:String,
                  @Field("password") password:String):Observable<BaseMdl<LoginResponseMdl>>


    @POST("checkout/submit")
    fun postCheckout(@Header("Authorization") token:String ,
                     @Header("StickHeader") auth:String,
                     @Body payloadRequestBaseMdl: PayloadRequestBaseMdl<CheckoutRequestMdl>):Observable<BaseMdl<CheckoutResponseMdl>>

    @POST("survey/start")
    fun startSurvey(@Header("StickHeader") token:String ,
                     @Header("Authorization") auth:String,
                     @Body payloadRequestBaseMdl: PayloadRequestBaseMdl<SurveyRequestMdl>):Observable<BaseMdl<SurveyQuestionResponseMdl>>

    @POST("survey/submit")
    fun submitAnswer(@Header("StickHeader") token:String ,
                    @Header("Authorization") auth:String,
                    @Body payloadRequestBaseMdl: PayloadRequestBaseMdl<SubmitAnswerRequestMdl>):Observable<BaseMdl<SurveyQuestionResponseMdl>>

    @GET("survey/detail/{survey_uuid}/question/{question_number}")
    fun getNextQuestion(@Header("StickHeader") auth: String,
                        @Header("Authorization") token:String,
                        @Path ("survey_uuid") survey_uuid:String,
                        @Path ("question_number") question_number:Int):Observable<BaseMdl<SurveyQuestionResponseMdl>>

    @FormUrlEncoded
    @POST("notification/show")
    fun getNotificationCount(@Header("Authorization") token:String,
                             @Header("StickHeader") auth:String,
                             @Query("page") page:Int, @Field("room")room:Int):Observable<BaseMdl<List<NotificationResponseMdl>>>

    @GET("points/user/")
    fun getPoint(@Header("Authorization") token:String,
                 @Header("StickHeader") auth:String):Observable<BaseMdl<PointResponseMdl>>

    @GET("points/user/{id}/history")
    fun getPointHistory(@Header("Authorization") token:String ,
                        @Header("StickHeader") auth:String,
                        @Path("id") uuid:String,
                        @Query("page")page:Int):Observable<BaseMdl<List<PointHistoryResponseMdl>>>


    @GET("profile/show")
    fun getProfile(@Header("Authorization") token:String ,@Header("StickHeader") auth:String):Observable<BaseMdl<ProfileResponseMdl>>

    @POST("profile/save")
    fun postEditProfile(@Header("Authorization") token:String ,@Header("StickHeader") auth:String,@Body data:PayloadRequestBaseMdl<EdiProfileRequestMdl>):Observable<BaseMdl<ProfileResponseMdl>>


    @POST("policy/show")
    fun getPolicy(@Header("Authorization") token:String ,@Header("StickHeader") auth:String, @Body hashMap: HashMap<String,Int>):Observable<BaseMdl<PolicyMdl>>

    @GET("voucher/list")
    fun getVoucher(@Header("Authorization") token:String ,
                   @Header("StickHeader") auth:String,
                   @Query("page") page:Int):Observable<BaseMdl<List<VoucherMdl>>>

    @GET("voucher/myvoucher")
    fun getMyVoucher(@Header("Authorization") token:String ,
                     @Header("StickHeader") auth:String,
                     @Query("page") page:Int):Observable<BaseMdl<List<VoucherMdl>>>

    @GET("voucher/history")
    fun getVoucherHistory(@Header("Authorization") token:String ,
                          @Header("StickHeader") auth:String):Observable<BaseMdl<List<VoucherMdl>>>

    @POST("voucher/purchased")
    fun postPurchasedVoucher(@Header("Authorization") token:String,
                             @Header("StickHeader") stickHeader:String,
                             @Body payloadRequestBaseMdl: PayloadRequestBaseMdl<PurchasedRequestMdl>):Observable<BaseMdl<PurchasedResponseMdl>>

    @POST("voucher/redeem")
    fun postRedeemVoucher(@Header("Authorization") token:String,
                          @Header("StickHeader") stickHeader:String,
                          @Body payloadRequestBaseMdl: PayloadRequestBaseMdl<PurchasedRequestMdl>):Observable<BaseMdl<PurchasedResponseMdl>>

    @GET("notification/detail/{id}")
    fun getNotifDetail(@Header("Authorization") token:String ,@Header("StickHeader") auth:String, @Path("id")id:Int):Observable<BaseMdl<NotificationResponseMdl>>

    @GET("order/detail/{order_uuid}?include=items")
    fun getOrderDetail(@Header("Authorization") token:String ,@Header("StickHeader") auth:String, @Path("order_uuid")order_uuid:String):Observable<BaseMdl<OrderDetailMdl>>

    @POST("pin")
    fun postOtpPin(@Body data: OtpPinRequestMdl):Observable<BaseMdl<OtpPinResponseMdl>>

    @POST("resend-pin")
    fun postResendOtpPin(@Body data: ResendOtpPinRequestMdl):Observable<BaseMdl<ResendOtpPinResponseMdl>>

    @GET("survey/list")
    fun getSurveyList(@Header("Authorization") token: String, @Header("StickHeader") auth: String):Observable<BaseMdl<List<SurveyResponseMdl>>>

    @GET("survey/detail/{survey_uuid}")
    fun getSurveyDetail(@Header("Authorization") token: String, @Header("StickHeader") auth: String, @Path("survey_uuid") surveyUuid: String):Observable<BaseMdl<SurveyResponseMdl>>

    @GET("survey/available")
    fun getSurveyAvailable(@Header("Authorization") token: String, @Header("StickHeader") auth: String):Observable<BaseMdl<SurveyResponseMdl>>


    @GET("activity/list")
    fun getUserHistoryList(@Header("Authorization") token: String, @Header("StickHeader") auth: String):Observable<BaseMdl<List<UserHistoryResponseMdl>>>


}