package com.stickearn.stickpass.model

data class ErrorResponseMdl(val httpCode: Int = 0,
                            val message: String = "",
                            val status: Boolean = false)