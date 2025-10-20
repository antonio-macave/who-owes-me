package mz.co.macave.whoowesme.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateDebtViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> get() = _name.asStateFlow()

    private val _surname = MutableStateFlow("")
    val surname: StateFlow<String> get() = _surname.asStateFlow()

    private val _dueDate = MutableStateFlow("")
    val dueDate: StateFlow<String> get() = _dueDate.asStateFlow()

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> get() = _description.asStateFlow()

    private val _showDueDateDialog = MutableStateFlow(false)
    val showDueDateDialog: StateFlow<Boolean> get() = _showDueDateDialog.asStateFlow()

    private val _amount = MutableStateFlow(0.0)
    val amount: StateFlow<Double> get() = _amount.asStateFlow()

    fun updateName(newValue: String) {
        _name.value = newValue
    }

    fun updateSurname(newValue: String) {
        _surname.value = newValue
    }

    fun updateDueDate(newValue: String) {
        _dueDate.value = newValue
    }

    fun updateDescription(newValue: String) {
        _description.value = newValue
    }

    fun updateShowDueDateDialog(newValue: Boolean) {
        _showDueDateDialog.value = newValue
    }

}