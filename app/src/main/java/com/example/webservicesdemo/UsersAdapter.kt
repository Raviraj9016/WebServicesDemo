package com.example.webservicesdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webservicesdemo.databinding.UserViewBinding

class UsersAdapter(var users : ArrayList<User>): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userViewBinding : UserViewBinding = UserViewBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
       var userView = layoutInflater.inflate(R.layout.user_view,null)
        return UserViewHolder(userView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userViewBinding.txtUserId.text = "${users[position].id}"
        holder.userViewBinding.txtUserName.text = "${users[position].first_name} ${users[position].last_name}"
        Glide.with(holder.userViewBinding.root)
            .load(users[position].avatar)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.userViewBinding.imgUser)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}