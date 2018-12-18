package com.stickearn.stickpass.helper

import android.annotation.SuppressLint
import android.util.Log.wtf
import java.text.SimpleDateFormat
import java.util.*
import java.text.ParseException


/**
 * Created by oohyugi on 3/14/18.
 */
class DateHelper {

    companion object {
        //sample jan 01, 2017
       val DATE_OUTPUT_v1:String  = "MMM dd, yyyy"

        //2018-03-14 11:00:00
       val DATE_INPUT_v1:String  = "yyyy-MM-dd HH:mm:ss"


        fun getDateNow(format: String): String {
            val dateFormat = SimpleDateFormat(format)
            val date = Date()
            return dateFormat.format(date)
        }

        @SuppressLint("SimpleDateFormat")
        fun convertDateFromApi(dateString: String, format: String): String {
            val date = SimpleDateFormat(DATE_INPUT_v1).parse(dateString)
            val newsDate = SimpleDateFormat(format).format(date)
            return newsDate.toString()
        }

        @SuppressLint("SimpleDateFormat")
        fun calculateDate(startDate: String, endDate: String): Long {
            //HH converts hour in 24 hours format (0-23), day calculation
            val format = SimpleDateFormat(DateHelper.DATE_INPUT_v1)

            var d1: Date? = null
            var d2: Date? = null
            var diffHours: Long = 0
            var day: Long = 0
            var diffMinutes: Long = 0

            try {
                d1 = format.parse(startDate)
                d2 = format.parse(endDate)

                //in milliseconds
                val diff = d2!!.time - d1!!.time

                val diffSeconds = diff / 1000 % 60

                diffMinutes = diff / (60 * 1000) % 60

                diffHours = diff / (60 * 60 * 1000) % 24
                val diffDays = diff / (24 * 60 * 60 * 1000)
                day = diff * 24
                print(diffDays.toString() + " days, ")
                print(diffHours.toString() + " hours, ")
                print(diffMinutes.toString() + " minutes, ")
                print(diffSeconds.toString() + " seconds.")

                wtf("startDate",startDate)
                wtf("endDate",endDate)

                wtf("different",diffMinutes.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return diffMinutes

        }

        fun getTimeStamp(): String {
            val tsLong = System.currentTimeMillis() / 1000
            return tsLong.toString()
        }

        fun beforeAfterDate(startDate: String,endDate: String):Boolean{

            val dateFormat = SimpleDateFormat(
                    DATE_INPUT_v1)
            var convertedDate = Date()
            var convertedDate2 = Date()
            var isBolean =false
            try {
                convertedDate = dateFormat.parse(startDate)
                convertedDate2 = dateFormat.parse(endDate)
                isBolean = convertedDate.after(convertedDate2)
            } catch (e: ParseException) {

                e.printStackTrace()
            }
            return isBolean

        }
    }







}