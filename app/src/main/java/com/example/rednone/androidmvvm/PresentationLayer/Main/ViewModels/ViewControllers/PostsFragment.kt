package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewControllers


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.example.rednone.androidmvvm.DataLayer.Models.PostModel
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.Adapters.PostsAdapter
import com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.ViewModels.PostsViewModel

import com.example.rednone.androidmvvm.R
import kotlinx.android.synthetic.main.fragment_posts.*


/**
 * A simple [Fragment] subclass.
 */
class PostsFragment : Fragment() {

    private lateinit var viewModel: PostsViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        postContentView.setOnRefreshListener {
            viewModel.loadPosts()
        }

        getPosts()
    }

    private fun getPosts() {
        viewModel.getPosts()?.observe(this, Observer {
            postContentView.isRefreshing = false
            if (it?.error != null) {
                errorView.text = it.error.localizedMessage
            } else if (it?.data != null) {
                configureRecyclerView(it.data)
                loadingView.visibility = GONE
            }
        })
    }

    private fun configureRecyclerView(posts: List<PostModel>) {
        val adapter = PostsAdapter(posts)
        postsRecyclerView.layoutManager = LinearLayoutManager(activity)
        postsRecyclerView.adapter = adapter
    }
}
