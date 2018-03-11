package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewControllers

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewModels.MainViewModel
import com.example.rednone.androidmvvm.R
import kotlinx.android.synthetic.main.activity_main.*
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewControllers.UsersFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        showFragment(item.itemId)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        homeNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        checkInitialFragment()
    }

    private fun checkInitialFragment() {
       if (!viewModel.isInitialFragmentSelected) {
           homeNavigation.selectedItemId = R.id.navigation_posts
           viewModel.isInitialFragmentSelected = true
       }
    }

    private fun showFragment(fragmentId: Int) {
        var fragment: Fragment? = null
        when(fragmentId) {
            R.id.navigation_posts -> fragment = PostsFragment()
            R.id.navigation_users -> fragment = UsersFragment()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentView, fragment)
        transaction.commit()
    }
}
