package com.example.rednone.androidmvvm.DataLayer.Interfaces

import com.example.rednone.androidmvvm.DataLayer.Models.PostModel
import com.example.rednone.androidmvvm.DataLayer.Models.UserModel
import io.reactivex.Observable

/**
 * Created by RedNone on 12.03.2018.
 */
interface ApiManager {
    fun getPosts(): Observable<List<PostModel>>
    fun getUsers(): Observable<List<UserModel>>
}