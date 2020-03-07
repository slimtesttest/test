package com.coyote.testonnech.ui.users.list

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coyote.data.db.tables.User
import com.coyote.testonnech.R
import com.travelcar.test.ui.main.car.list.UserListClickEventListener
import kotlinx.android.synthetic.main.user_item.view.*


class ListUsersAdapter(
    private val context: Context,
    private val userListClickEventListener: UserListClickEventListener
) : PagedListAdapter<User, ListUsersAdapter.UserItemViewHolder>(UserDiffCallback()) /*RecyclerView.Adapter<ListUsersAdapter.UserItemViewHolder>()*/ {
    private var usersList: List<User> = listOf()
    private var filter: String = ""

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.user_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(@NonNull holder: UserItemViewHolder, position: Int) {
        val user = usersList[position]
        holder.displayData(user)
    }

    fun setData(newData: List<User>, filter: String) {
        this.filter = filter
        usersList = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return usersList.size
    }



    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }


    //ViewHolder
    inner class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.name
        private val email: TextView = itemView.email
        private val image: AppCompatImageView = itemView.image

        fun displayData(user: User) {
            name.text = getHighlightText(user)
            email.text = user.email
            Glide.with(context).load(user.picture.thumbnail).into(image)
            itemView.item_user.setOnClickListener {
                userListClickEventListener.onUserItemClick(user, image)
            }
        }

        private fun getHighlightText(user: User): CharSequence {
            return if (filter.isNotBlank()) {
                val startPos = user.name.last.toUpperCase().indexOf(filter.toUpperCase())
                val endPos = startPos + filter.length
                val spannable = SpannableString(user.name.last)
                val styleSpan = StyleSpan(Typeface.BOLD)
                if (startPos >= 0)
                spannable.setSpan(styleSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable
            } else {
                user.name.last
            }
        }
    }
}