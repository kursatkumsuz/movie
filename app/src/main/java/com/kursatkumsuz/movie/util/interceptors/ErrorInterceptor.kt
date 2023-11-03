package com.kursatkumsuz.movie.util.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.lang.RuntimeException

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            throw Exception(
                errorMessage = response.message,
                errorCode = response.code
            )
        }
        return response
    }

    class Exception(
        val errorMessage: String,
        val errorCode: Int
    ) : RuntimeException(errorMessage)


}