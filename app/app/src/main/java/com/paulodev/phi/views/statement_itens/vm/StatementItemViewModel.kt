package com.paulodev.phi.views.statement_itens.vm

import android.view.View
import androidx.lifecycle.ViewModel
import com.paulodev.phi.repository.api.messages.StatementMessage
import com.paulodev.phi.util.format

class StatementItemViewModel(
    val origin: String,
    val description: String,
    val amount: String,
    val pix: Boolean,
    val type: StatementMessage.StatementType,
    val date: String
) :
    ViewModel() {

    lateinit var onClick : View.OnClickListener

    companion object {
        fun translateMessage(message: StatementMessage): StatementItemViewModel {
            return StatementItemViewModel(
                message.getOrigin(),
                message.description,
                message.formatedNumber(),
                message.type == StatementMessage.StatementType.PIXCASHIN || message.type == StatementMessage.StatementType.PIXCASHOUT,
                message.type,
                message.date.format("dd/MM")
            )
        }
    }
}