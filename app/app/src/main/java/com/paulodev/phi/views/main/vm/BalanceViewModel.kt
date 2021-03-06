package com.paulodev.phi.views.main.vm

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.paulodev.phi.R
import com.paulodev.phi.repository.AccountRepo
import com.paulodev.phi.util.Constants
import com.paulodev.phi.util.PreferenceUtil
import com.paulodev.phi.util.formatNumber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class BalanceViewModel(private val accountRepo: AccountRepo, application: Application) : AndroidViewModel(
    application
) {
    var isVisible = MutableLiveData(false)
    var amount = MutableLiveData("-")

    init {
        isVisible.postValue(isAmountHide())
    }

    var showHideBalance = View.OnClickListener { showHideAmount() }

    fun loadBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = accountRepo.getBalance()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        amount.postValue(response.body()?.amount?.formatNumber())
                    }
                }
            } catch(e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        getApplication(),
                        (getApplication() as Context).getString(R.string.exception_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun isAmountHide(): Boolean {
        return PreferenceUtil.isBalanceVisible(getApplication())
    }

    private fun showHideAmount() {
        PreferenceUtil.saveBooleanPref(getApplication(), Constants.Preferences.balanceVisibility, !isVisible.value!!)
        isVisible.postValue(!isVisible.value!!)
    }


}