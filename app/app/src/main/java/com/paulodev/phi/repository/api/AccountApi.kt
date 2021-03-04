package com.paulodev.phi.repository.api

import com.paulodev.phi.repository.api.messages.BalanceMessage
import com.paulodev.phi.repository.api.messages.ListStatementsResponse
import com.paulodev.phi.repository.api.messages.StatementMessage
import com.paulodev.phi.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {

    @GET(Constants.Endpoints.myBalance)
    suspend fun getBalance(): Response<BalanceMessage>

    @GET(Constants.Endpoints.myStatement)
    suspend fun getStatements(
        @Path("limit") limit: Int,
        @Path("offset") offset: Int
    ): Response<ListStatementsResponse>

    @GET(Constants.Endpoints.statementDetail)
    suspend fun getStatementDetail(@Path("id") id: String) : Response<StatementMessage>
}