package com.coyote.data.gateway

import com.coyote.data.db.dao.TripDao
import com.coyote.data.mapper.UserMapper
import com.coyote.data.network.CoyoteWebService
import com.coyote.domain.usecases.remote.GetUsersRemoteGateway
import io.reactivex.schedulers.Schedulers

class GetUsersRemoteGatewayImpl(
    private val coyoteWebService: CoyoteWebService,
    private val userDao: TripDao
) :
    GetUsersRemoteGateway {
    override fun getRemoteUsers(page: Int) {
        coyoteWebService.fetchUsersFromRemote(page)
            .map { it.results }
            .observeOn(Schedulers.io())
            .subscribe {
                userDao.insertUsers(UserMapper.mapListUserToEntity(it))
            }
    }
}