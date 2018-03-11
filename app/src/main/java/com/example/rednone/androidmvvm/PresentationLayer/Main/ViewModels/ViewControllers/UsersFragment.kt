package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewControllers


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.example.rednone.androidmvvm.DataLayer.Models.PostModel
import com.example.rednone.androidmvvm.DataLayer.Models.UserModel
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.Adapters.PostsAdapter
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.Adapters.UsersAdapter
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewModels.UsersViewModel

import com.example.rednone.androidmvvm.R
import kotlinx.android.synthetic.main.fragment_users.*


/**
 * A simple [Fragment] subclass.
 */
class UsersFragment : Fragment() {

    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)
        usersContentView.setOnRefreshListener {
            viewModel.loadUsers()
        }

        getUsers()
    }

    private fun getUsers() {
        viewModel.getUsers()?.observe(this, Observer {
            usersContentView.isRefreshing = false
            if (it?.error != null) {
                errorView.text = it.error.localizedMessage
            } else if (it?.data != null) {
                configureRecyclerView(it.data)
                loadingView.visibility = GONE
            }
        })
    }

    private fun configureRecyclerView(users: List<UserModel>) {
        val adapter = UsersAdapter(users)
        usersRecyclerView.layoutManager = LinearLayoutManager(activity)
        usersRecyclerView.adapter = adapter
    }
}
