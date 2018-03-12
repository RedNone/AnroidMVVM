package com.example.rednone.androidmvvm.DataLayer.Managers

import com.example.rednone.androidmvvm.DataLayer.Interfaces.ApiManager
import com.example.rednone.androidmvvm.DataLayer.Interfaces.JsonPlaceholderApi
import com.example.rednone.androidmvvm.DataLayer.Models.PostModel
import com.example.rednone.androidmvvm.DataLayer.Models.UserModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by RedNone on 11.03.2018.
 */
class ApiManagerImplementation : ApiManager {
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    private val jsonPlaceholderApi = create()

    private fun create(): JsonPlaceholderApi {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

        return retrofit.create(JsonPlaceholderApi::class.java)
    }

    override fun getPosts(): Observable<List<PostModel>> = jsonPlaceholderApi.getPosts()

    override fun getUsers(): Observable<List<UserModel>> = jsonPlaceholderApi.getUsers()
}