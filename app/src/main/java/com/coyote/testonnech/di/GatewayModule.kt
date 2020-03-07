package com.coyote.testonnech.di

import com.coyote.data.gateway.GetUsersRemoteGatewayImpl
import com.coyote.domain.usecases.remote.GetUsersRemoteGateway
import org.koin.dsl.module

val GatewayModule = module {
    single { GetUsersRemoteGatewayImpl(get()) as GetUsersRemoteGateway }
}