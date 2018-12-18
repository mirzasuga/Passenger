package com.stickearn.stickpass.helper

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by oohyugi on 3/9/18.
 */
class StringHelperTest {


    @Test
    fun toBase64() {
        StringHelper.fromBase64("eyJhY2NvdW50Ijp7ImlkIjoxLCJuYW1lIjoiSm9keSBDYXJ0ZXIiLCJlbWFpbCI6Imh1ZHNvbi5hbWllQGtpaG4uY29tIiwidXVpZF9hZG1pbiI6IjI2ZmM0YzE0LTQwODctNDJiZC1hNjAwLTU2MzhmMmJhMjdlNCIsIm1lbnVfYWRtaW4iOlt7ImlkIjoxLCJncm91cCI6MSwibmFtZSI6IkRhc2hib2FyZCIsImljb24iOiJkYXNoYm9hcmQiLCJ1cmwiOiJcL2Rhc2hib2FyZCIsImNyZWF0ZWRfYXQiOm51bGwsInVwZGF0ZWRfYXQiOm51bGwsImRlbGV0ZWRfYXQiOm51bGx9LHsiaWQiOjIsImdyb3VwIjoxLCJuYW1lIjoiQ2FtcGFpZ24iLCJpY29uIjoidGlja2V0IiwidXJsIjoiXC9jYW1wYWlnbiIsImNyZWF0ZWRfYXQiOm51bGwsInVwZGF0ZWRfYXQiOm51bGwsImRlbGV0ZWRfYXQiOm51bGx9XSwidXVpZF9hZHZlcnRpc2luZyI6bnVsbCwibWVudV9hZHZlcnRpc2luZyI6W10sInV1aWRfbWFydCI6bnVsbCwibWVudV9tYXJ0IjpbXSwidXVpZF9wYXNzZW5nZXIiOm51bGwsIm1lbnVfcGFzc2VuZ2VyIjpbXSwidXVpZF9wb2ludCI6bnVsbCwibWVudV9wb2ludCI6W10sInV1aWRfc3VydmV5IjpudWxsLCJtZW51X3N1cnZleSI6W10sInBob25lIjpudWxsLCJwcm92aWRlciI6bnVsbCwicHJvdmlkZXJfaWQiOm51bGwsImFjdGl2ZSI6MSwiY3JlYXRlZF9hdCI6eyJkYXRlIjoiMjAxOC0wMS0xOCAxMDoxNzozMi4wMDAwMDAiLCJ0aW1lem9uZV90eXBlIjozLCJ0aW1lem9uZSI6IkFzaWFcL0pha2FydGEifSwidXBkYXRlZF9hdCI6eyJkYXRlIjoiMjAxOC0wMS0yNCAxMDoxODozMS4wMDAwMDAiLCJ0aW1lem9uZV90eXBlIjozLCJ0aW1lem9uZSI6IkFzaWFcL0pha2FydGEifX19")
    }

}