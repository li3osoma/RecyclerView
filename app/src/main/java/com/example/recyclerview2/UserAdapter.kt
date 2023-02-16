package com.example.recyclerview2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview2.databinding.ItemUserBinding
import com.example.recyclerview2.model.User

//interface UserActionListener{
//    fun onUserMove(user: User, moveBy: Int)
//    fun onUserDelete(user:User)
//    fun onUserDetails(user: User)
//}

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    var users: List<User> = emptyList()
        set(newValue){
            field=newValue
            notifyDataSetChanged()
        }

    class UserViewHolder(val binding: ItemUserBinding)
        :RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ItemUserBinding.inflate(inflater, parent,false)
//        binding.root.setOnClickListener(this)
//        binding.moreButton.setOnClickListener(this)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int=users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user=users[position]
        with(holder.binding){
            holder.itemView.tag=user
            moreButton.tag=user

            userNameText.text=user.name
            userCompanyText.text=user.company
            if(user.photo.isNotBlank()) {
                Glide.with(userAvatar.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_avatar)
                    .error(R.drawable.ic_user_avatar)
                    .into(userAvatar)
            }
            else{
                userAvatar.setImageResource(R.drawable.ic_user_avatar)
            }
        }
    }

//    override fun onClick(v: View) {
//        val user:User=v.tag as User
//        when(user.id){
//            R.id.moreButton -> {
//
//            }
//            else -> {
//                actionListen
//            }
//        }
}