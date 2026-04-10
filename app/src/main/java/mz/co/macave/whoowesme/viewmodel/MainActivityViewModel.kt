package mz.co.macave.whoowesme.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import mz.co.macave.whoowesme.model.Debt
import mz.co.macave.whoowesme.util.DebtStatus

class MainActivityViewModel: ViewModel() {

    private val _fabMenuExpanded = MutableStateFlow(false)
    val fabMenuExpanded: StateFlow<Boolean> get() = _fabMenuExpanded.asStateFlow()

    private val _cardExpanded = MutableStateFlow<Int?>(null)
    val cardExpanded: StateFlow<Int?> get() = _cardExpanded.asStateFlow()

    private val _showSortDebtsDialog = MutableStateFlow(false)
    val showSortDebtsDialog: StateFlow<Boolean> get() = _showSortDebtsDialog.asStateFlow()



    fun filterDebts(debts: List<Debt>, all: Boolean, pending: Boolean, paid: Boolean, overdue: Boolean): List<Debt> {
        return debts.filter { debt ->
            when (debt.status) {
                DebtStatus.PENDING.code -> pending
                DebtStatus.PAID.code -> paid
                DebtStatus.OVERDUE.code -> overdue
                else -> all
            }
        }
    }

    fun updateShowSortDebtsDialog(show: Boolean) {
        _showSortDebtsDialog.value = show
    }


    fun updateFabMenuExpanded(expanded: Boolean) {
        _fabMenuExpanded.value = expanded
    }

    fun updateCardExpanded(id: Int) {
        _cardExpanded.value = if (_cardExpanded.value == id) null else id
    }
}