package com.paulodev.phi.views.statement_details.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulodev.phi.repository.AccountRepo
import com.paulodev.phi.repository.api.messages.StatementMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatementDetailViewModel(private val accountRepo: AccountRepo) : ViewModel() {

    var details = MutableLiveData<StatementMessage>()

    fun getDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = accountRepo.getStatementDetails(id)
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    details.value = response.body()
                }
            }
        }
    }
}