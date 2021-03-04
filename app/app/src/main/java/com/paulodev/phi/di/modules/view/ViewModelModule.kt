package com.paulodev.phi.di.modules.view

import android.app.Application
import com.paulodev.phi.di.modules.factory.ViewModelFactory
import com.paulodev.phi.repository.AccountRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(accountRepo: AccountRepo, application: Application) = ViewModelFactory(accountRepo, application)
}