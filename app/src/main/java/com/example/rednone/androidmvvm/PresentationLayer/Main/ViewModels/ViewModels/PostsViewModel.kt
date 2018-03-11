package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rednone.androidmvvm.DataLayer.Interfaces.JsonPlaceholderApi
import com.example.rednone.androidmvvm.DataLayer.Models.LiveDataResult
import com.example.rednone.androidmvvm.DataLayer.Models.PostModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by RedNone on 10.03.2018.
 */
class PostsViewModel: ViewModel() {

    private var postsData: MutableLiveData<LiveDataResult<List<PostModel>>> = MutableLiveData()
    private var posts: LiveDataResult<List<PostModel>>?= null
    private val compositeDisposable = CompositeDisposable()

    fun getPosts(): LiveData<LiveDataResult<List<PostModel>>>? {
        if (posts != null && posts?.data?.isEmpty() != true) {
            postsData.value = posts
        } else {
            loadPosts()
        }
        return postsData
    }

    fun loadPosts() {
        val disposable = JsonPlaceholderApi
                .create()
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                              posts =  LiveDataResult(it, null)
                              postsData?.value = LiveDataResult(it, null) },
                             Consumer {
                                         posts =  LiveDataResult(null, it)
                                         postsData?.value = LiveDataResult(null, it) })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}