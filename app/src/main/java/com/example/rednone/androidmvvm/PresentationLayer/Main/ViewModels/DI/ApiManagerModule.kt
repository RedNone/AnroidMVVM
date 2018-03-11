package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.DI

import android.support.annotation.NonNull
import com.example.rednone.androidmvvm.DataLayer.Managers.ApiManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by RedNone on 11.03.2018.
 */
@Module
class ApiManagerModule {

    @Provides
    @NonNull
    @Singleton
    fun provideApiManager(): ApiManager = ApiManager()
}
