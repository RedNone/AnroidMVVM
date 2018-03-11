package com.example.rednone.androidmvvm.DataLayer.Managers

import com.example.rednone.androidmvvm.DataLayer.Interfaces.JsonPlaceholderApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by RedNone on 11.03.2018.
 */
class ApiManager {

    private val baseUrl = "https://jsonplaceholder.typicode.com"

    val jsonPlaceholderApi = create()

    private fun create(): JsonPlaceholderApi {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

        return retrofit.create(JsonPlaceholderApi::class.java)
    }
}