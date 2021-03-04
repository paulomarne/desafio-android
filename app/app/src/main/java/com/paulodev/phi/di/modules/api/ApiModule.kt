package com.paulodev.phi.di.modules.api

import com.paulodev.phi.repository.api.AccountApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideAccountApi(retrofit: Retrofit): AccountApi = retrofit.create(AccountApi::class.java)
}