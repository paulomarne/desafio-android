package com.paulodev.phi.di.component

import android.app.Application
import com.paulodev.phi.di.modules.api.ApiModule
import com.paulodev.phi.di.modules.api.RetrofitModule
import com.paulodev.phi.di.modules.*
import com.paulodev.phi.di.modules.view.ViewModelModule
import com.paulodev.phi.views.main.MainActivity
import com.paulodev.phi.views.statement_details.StatementDetailsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PhiAppModule::class,
        RetrofitModule::class,
        ApiModule::class,
        RepoModule::class,
        ViewModelModule::class
    ]
)
interface PhiAppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(statementDetailsActivity: StatementDetailsActivity)
    fun application(): Application
}