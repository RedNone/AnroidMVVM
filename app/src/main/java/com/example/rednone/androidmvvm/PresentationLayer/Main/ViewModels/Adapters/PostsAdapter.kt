package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rednone.androidmvvm.DataLayer.Models.PostModel
import com.example.rednone.androidmvvm.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.posts_cell.view.*

/**
 * Created by RedNone on 11.03.2018.
 */
class PostsAdapter(val posts: List<PostModel>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(posts[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.posts_cell, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindData(post: PostModel) {
            itemView.postTitleView.text = post.title
            itemView.postDescriptionView.text = post.body
        }
    }
}