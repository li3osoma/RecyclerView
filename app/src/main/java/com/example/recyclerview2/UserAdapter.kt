package com.example.recyclerview2

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview2.databinding.ItemUserBinding
import com.example.recyclerview2.model.User

interface UserActionListener{
    fun onUserMove(user: User, moveBy: Int)
    fun onUserDelete(user:User)
    fun onUserDetails(user: User)
}

class UserAdapter(
    private val actionListener: UserActionListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), View.OnClickListener{

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
        binding.root.setOnClickListener(this)
        binding.moreButton.setOnClickListener(this)
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


    override fun onClick(v: View) {
        val user: User = v.tag as User
        when(v.id){
            R.id.moreButton ->{
                showPopupMenu(v)
            }
            else -> {
                actionListener.onUserDetails(user)
            }
        }
    }

    private fun showPopupMenu(view:View){
        val popupMenu:PopupMenu=PopupMenu(view.context, view)
        val contextFunctionTypeParams = view.context
        val user=view.tag as User
        val position=users.indexOfFirst { it.id==user.id }

        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, "Move up").apply{
            isEnabled=position>0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, "Move down").apply {
            isEnabled=position<users.size-1
        }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, "Remove")

        popupMenu.setOnMenuItemClickListener{
            when(it.itemId){
                ID_MOVE_UP -> {
                    actionListener.onUserMove(user,-1)
                }
                ID_MOVE_DOWN -> {
                    actionListener.onUserMove(user, 1)
                }
                ID_REMOVE -> {
                    actionListener.onUserDelete(user)
                }

            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()

    }

    companion object{
        private const val ID_MOVE_UP=1
        private const val ID_MOVE_DOWN=2
        private const val ID_REMOVE=3
    }
}