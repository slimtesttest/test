package com.travelcar.test.ui.main.car.list

import androidx.appcompat.widget.AppCompatImageView
import com.coyote.data.db.tables.User

interface UserListClickEventListener {
    fun onUserItemClick(user: User, sharedElementView: AppCompatImageView)
}