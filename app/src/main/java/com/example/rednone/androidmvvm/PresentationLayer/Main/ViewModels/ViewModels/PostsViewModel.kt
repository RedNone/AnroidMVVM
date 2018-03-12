package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rednone.androidmvvm.DataLayer.Interfaces.ApiManager
import com.example.rednone.androidmvvm.DataLayer.Models.LiveDataResult
import com.example.rednone.androidmvvm.DataLayer.Models.PostModel
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.DI.ApiManagerModule
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.DI.DaggerAppComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by RedNone on 10.03.2018.
 */
class PostsViewModel: ViewModel() {

    @Inject
    lateinit var apiManager: ApiManager

    private var isSyncing = false
    private var postsData: MutableLiveData<LiveDataResult<List<PostModel>>> = MutableLiveData()
    private var posts: LiveDataResult<List<PostModel>>?= null
    private val compositeDisposable = CompositeDisposable()

    init {
        val graph = DaggerAppComponent.builder().apiManagerModule(ApiManagerModule()).build()
        graph.inject(this)
    }

    fun getPosts(): LiveData<LiveDataResult<List<PostModel>>>? {
        if (posts != null && posts?.data?.isEmpty() != true) {
            postsData.value = posts
        } else if(!isSyncing) {
            loadPosts()
        }
        return postsData
    }

    fun loadPosts() {
        isSyncing = true
        val disposable = apiManager
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    isSyncing = false
                    posts =  LiveDataResult(it, null)
                    postsData?.value = LiveDataResult(it, null) },
                             Consumer {
                                 isSyncing = false
                                 posts =  LiveDataResult(null, it)
                                 postsData?.value = LiveDataResult(null, it) })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}