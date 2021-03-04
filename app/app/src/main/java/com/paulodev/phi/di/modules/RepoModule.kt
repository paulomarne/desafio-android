package com.paulodev.phi.di.modules

import com.paulodev.phi.repository.AccountRepo
import com.paulodev.phi.repository.api.AccountApi
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun provideAccountRepo(accountApi: AccountApi) = AccountRepo(accountApi)
}