package com.example.rednone.androidmvvm.PresentationLayer.Main.ViewModels.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rednone.androidmvvm.DataLayer.Models.UserModel
import com.example.rednone.androidmvvm.R
import kotlinx.android.synthetic.main.user_cell.view.*

/**
 * Created by RedNone on 11.03.2018.
 */
class UsersAdapter(val users: List<UserModel>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(users[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.user_cell, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(user: UserModel) {
            itemView.textViewEmailContainer.text = user.email
            itemView.textViewNameContainer.text = user.name
            itemView.textViewPhoneContainer.text = user.phone
            itemView.textViewWebsiteContainer.text = user.website
        }
    }
}