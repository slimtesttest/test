package com.coyote.testonnech.di

import com.coyote.testonnech.ui.users.list.UserPagedListRepository
import org.koin.dsl.module

val RepoModule = module {
    single { UserPagedListRepository(get()) }
}