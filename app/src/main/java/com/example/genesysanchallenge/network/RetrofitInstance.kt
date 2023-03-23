package com.example.genesysanchallenge.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        val BASE_URL = "https://randomuser.me"

        val interceptor = HttpLoggingInterceptor()
        val httpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        fun getRetroInstance(): Retrofit {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}