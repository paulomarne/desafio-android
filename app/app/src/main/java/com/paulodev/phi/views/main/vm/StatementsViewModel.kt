package com.paulodev.phi.views.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.paulodev.phi.repository.AccountRepo
import com.paulodev.phi.repository.sources.StatementsPageSource

class StatementsViewModel(private val accountRepo: AccountRepo) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 10)
    ) {
        StatementsPageSource(accountRepo)
    }.flow.cachedIn(viewModelScope)
}