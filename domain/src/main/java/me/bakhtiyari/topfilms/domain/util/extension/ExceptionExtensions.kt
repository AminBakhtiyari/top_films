package me.bakhtiyari.topfilms.domain.util.extension

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Exception.handle(): String {
    this.printStackTrace()
    return when (this) {
        is HttpException -> ""
        is SocketTimeoutException -> "لطفا اتصال اینترنت خود را بررسی کنید"
        is UnknownHostException -> "لطفا تنظیمات اتصال اینترنت خود را بررسی کنید"
        else -> "ارور نامشخص.با مدیر سیستم تماس بگیرید"
    }
}