package com.paulodev.phi.util

class Constants {

    class Endpoints {
        companion object {
            const val myBalance = "myBalance"
            const val myStatement = "myStatement/{limit}/{offset}"
            const val statementDetail = "myStatement/detail/{id}"
        }
    }

    class Preferences {
        companion object {
            const val balanceVisibility = "balance_visibility"
        }
    }

    companion object {
        const val baseURL = "https://desafio-mobile-bff.herokuapp.com/"
        const val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    }

}