package com.coyote.testonnech.di

import com.coyote.data.network.CoyoteWebService
import com.coyote.testonnech.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val NetModule = module {

    single { provideInterceptLogging() }

    single { provideOkHttpClient(get()) }

    single { provideRetrofitClient(get()) }

    single { provideCoyoteWs(get()) }
}

fun provideInterceptLogging(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideOkHttpClient(loginInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loginInterceptor)
        .build()
}

fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideCoyoteWs(retrofit: Retrofit): CoyoteWebService {
    return retrofit.create(CoyoteWebService::class.java)
}


