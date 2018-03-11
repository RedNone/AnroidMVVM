package com.example.rednone.androidmvvm.DataLayer.Interfaces

import com.example.rednone.androidmvvm.DataLayer.Models.PostModel
import com.example.rednone.androidmvvm.DataLayer.Models.UserModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by RedNone on 04.03.2018.
 */
interface JsonPlaceholderApi {
    @GET("/posts")
    fun getPosts(): Observable<List<PostModel>>
    @GET("/users")
    fun getUsers(): Observable<List<UserModel>>

    companion object {
        fun create(): JsonPlaceholderApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .build()

            return retrofit.create(JsonPlaceholderApi::class.java)
        }
    }
}