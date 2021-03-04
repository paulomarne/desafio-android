package com.paulodev.phi.application

import android.app.Application
import com.paulodev.phi.di.component.DaggerPhiAppComponent
import com.paulodev.phi.di.component.PhiAppComponent
import com.paulodev.phi.di.modules.PhiAppModule
import com.paulodev.phi.di.modules.api.RetrofitModule

class PhiAppApplication : Application() {

    lateinit var phiAppComponent: PhiAppComponent

    override fun onCreate() {
        super.onCreate()

        phiAppComponent = DaggerPhiAppComponent.builder()
            .phiAppModule(PhiAppModule(this))
            .retrofitModule(RetrofitModule())
            .build()
    }
}