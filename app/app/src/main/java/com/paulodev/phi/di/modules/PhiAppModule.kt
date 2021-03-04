package com.paulodev.phi.di.modules

import android.app.Application
import com.paulodev.phi.application.PhiAppApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PhiAppModule(private val application: PhiAppApplication) {

    @Provides
    @Singleton
    fun providePhiAppApplication(application: PhiAppApplication): PhiAppApplication = application

    @Provides
    fun provideApplication(): Application = application
}