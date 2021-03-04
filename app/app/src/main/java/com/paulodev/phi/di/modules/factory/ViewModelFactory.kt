package com.paulodev.phi.di.modules.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulodev.phi.repository.AccountRepo
import com.paulodev.phi.views.main.vm.BalanceViewModel
import com.paulodev.phi.views.main.vm.StatementsViewModel
import com.paulodev.phi.views.statement_details.vm.StatementDetailViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(val accountRepo: AccountRepo, val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BalanceViewModel::class.java)) {
            return BalanceViewModel(accountRepo, application) as T
        }
        if(modelClass.isAssignableFrom(StatementsViewModel::class.java)) {
            return StatementsViewModel(accountRepo) as T
        }
        if(modelClass.isAssignableFrom(StatementDetailViewModel::class.java)) {
            return StatementDetailViewModel(accountRepo) as T
        }
        throw IllegalArgumentException("Invalid ViewModel")
    }
}