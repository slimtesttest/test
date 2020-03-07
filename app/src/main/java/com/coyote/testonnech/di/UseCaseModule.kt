package com.coyote.testonnech.di

import com.coyote.domain.usecases.remote.GetRemoteUsersUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    single { GetRemoteUsersUseCase(get()) }
}