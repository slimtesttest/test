package com.coyote.data.gateway

import androidx.paging.DataSource
import com.coyote.data.db.dao.TripDao
import com.coyote.data.mapper.UserMapper
import com.coyote.data.network.CoyoteWebService
import com.coyote.domain.model.User
import com.coyote.domain.usecases.local.GetUsersLocalGateway

class GetUsersLocalGatewayImpl(
    private val coyoteWebService: CoyoteWebService,
    private val userDao: TripDao
) : GetUsersLocalGateway {

    override fun getRemoteUsers(): DataSource.Factory<Int, User> {
        return userDao.selectAllUsers().map {
            UserMapper.mapUserToDomain(it)
        }
    }
}