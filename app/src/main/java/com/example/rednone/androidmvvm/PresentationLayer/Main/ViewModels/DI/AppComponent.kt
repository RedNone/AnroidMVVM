package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.DI

import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewModels.PostsViewModel
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewModels.UsersViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RedNone on 11.03.2018.
 */
@Component(modules = arrayOf(ApiManagerModule::class))
@Singleton
interface AppComponent {
    fun inject(postsViewModel: PostsViewModel)
    fun inject(usersViewModel: UsersViewModel)
}