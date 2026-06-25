package mz.co.macave.quemmedeve.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import mz.co.macave.quemmedeve.data.repository.DebtorRepository
import mz.co.macave.quemmedeve.model.Debt
import mz.co.macave.quemmedeve.model.Debtor
import mz.co.macave.quemmedeve.model.DebtorWithDebts
import java.time.LocalDate

class DebtorsActivityViewModel(val debtorsRepository: DebtorRepository) : ViewModel() {

    private val _cardExpanded = MutableStateFlow<Int?>(null)
    val cardExpanded: StateFlow<Int?> get() = _cardExpanded.asStateFlow()

    private val _showDebtorBottomSheet = MutableStateFlow(false)
    val showDebtorBottomSheet: StateFlow<Boolean> get() = _showDebtorBottomSheet.asStateFlow()

    private val _selectedDebtorWithDebts = MutableStateFlow<DebtorWithDebts?>(null)
    val selectedDebtorWithDebts: StateFlow<DebtorWithDebts?> get() = _selectedDebtorWithDebts.asStateFlow()


    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    val debtors = debtorsRepository.getAllDebtors()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val debtorsWithDebts = debtorsRepository.getAllDebtorsWithDebts().onEach {
        _isLoading.value = false
    }

    fun updateShowDebtorBottomSheet(newValue: Boolean) {
        _showDebtorBottomSheet.value = newValue
    }

    fun updateSelectedDebtorWithDebts(debtorWithDebts: DebtorWithDebts) {
        _selectedDebtorWithDebts.value = debtorWithDebts
    }

    fun updateCardExpanded(id: Int) {
        _cardExpanded.value = if (_cardExpanded.value == id) null else id
    }

    fun getTotalDebt(debts: List<Debt>): Double {
        return debts.sumOf { it.amount - it.paidAmount }
    }

    fun deleteDebtor(debtor: Debtor) {
        viewModelScope.launch {
            debtorsRepository.deleteDebtor(debtor)
        }
    }
}