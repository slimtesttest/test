package com.coyote.testonnech

import android.app.Application
import androidx.multidex.MultiDex
import com.coyote.testonnech.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class CoyoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //MultiDex.install(this)

        startKoin {
            androidLogger()
            androidContext(this@CoyoteApplication)
            modules(listOf(UserModule, UseCaseModule, GatewayModule, NetModule, RepoModule))
        }
    }
}