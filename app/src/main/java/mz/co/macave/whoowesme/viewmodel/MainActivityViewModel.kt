package mz.co.macave.whoowesme.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel: ViewModel() {

    private val _fabMenuExpanded = MutableStateFlow(false)
    val fabMenuExpanded: StateFlow<Boolean> get() = _fabMenuExpanded.asStateFlow()

    private val _cardExpanded = MutableStateFlow<Int?>(null)
    val cardExpanded: StateFlow<Int?> get() = _cardExpanded.asStateFlow()


    fun updateFabMenuExpanded(expanded: Boolean) {
        _fabMenuExpanded.value = expanded
    }

    fun updateCardExpanded(id: Int) {
        _cardExpanded.value = if (_cardExpanded.value == id) null else id
    }
}