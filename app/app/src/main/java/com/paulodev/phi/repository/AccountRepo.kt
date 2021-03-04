package com.paulodev.phi.repository

import androidx.lifecycle.ViewModel
import com.paulodev.phi.repository.api.AccountApi

class AccountRepo(private val accountApi: AccountApi) : ViewModel() {

    //GET Balance
    suspend fun getBalance() = accountApi.getBalance()

    //GET Statements
    suspend fun getStatements(perPage : Int, page: Int)  = accountApi.getStatements(perPage, page)

    //GET Statements Details
    suspend fun getStatementDetails(id: String) = accountApi.getStatementDetail(id)

}