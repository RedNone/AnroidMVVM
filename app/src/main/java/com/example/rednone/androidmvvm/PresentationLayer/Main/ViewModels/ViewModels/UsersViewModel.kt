package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.rednone.androidmvvm.DataLayer.Interfaces.ApiManager
import com.example.rednone.androidmvvm.DataLayer.Models.LiveDataResult
import com.example.rednone.androidmvvm.DataLayer.Models.UserModel
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
class UsersViewModel: ViewModel() {

    @Inject
    lateinit var apiManager: ApiManager

    private var isSyncing = false
    private var usersData: MutableLiveData<LiveDataResult<List<UserModel>>> = MutableLiveData()
    private var users: LiveDataResult<List<UserModel>>?= null
    private val compositeDisposable = CompositeDisposable()

    init {
        val graph = DaggerAppComponent.builder().apiManagerModule(ApiManagerModule()).build()
        graph.inject(this)
    }

    fun getUsers(): LiveData<LiveDataResult<List<UserModel>>>? {
        if (users != null && users?.data?.isEmpty() != true) {
            usersData.value = users
        } else if (!isSyncing){
            loadUsers()
        }
        return usersData
    }

    fun loadUsers() {
        isSyncing = true
        val disposable = apiManager
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    users =  LiveDataResult(it, null)
                    usersData?.value = LiveDataResult(it, null)
                    isSyncing = false },
                        Consumer {
                            users =  LiveDataResult(null, it)
                            usersData?.value = LiveDataResult(null, it)
                            isSyncing = false })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}