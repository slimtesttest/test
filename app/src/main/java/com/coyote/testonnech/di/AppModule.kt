package com.coyote.testonnech.di

import com.coyote.testonnech.ui.users.list.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val UserModule = module {

    // UsersListViewModel ViewModel
    viewModel { UsersListViewModel(get(), get ()) }
}