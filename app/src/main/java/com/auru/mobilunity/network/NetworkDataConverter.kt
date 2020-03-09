package com.auru.mobilunity.network

import com.auru.mobilunity.R
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

class NetworkDataConverter {

    companion object {
        fun convertRestErrorToMessageId(e: Exception): Int =
            when (e) {
                is HttpException -> R.string.error_something_went_wrong
                is SocketTimeoutException, is IOException -> R.string.error_inet_unavailable //todo add check about inet availability
                else -> R.string.error_something_went_wrong
            }
    }

}