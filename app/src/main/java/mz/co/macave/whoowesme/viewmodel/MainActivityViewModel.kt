package mz.co.macave.whoowesme.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel: ViewModel() {

    private val _fabMenuExpanded = MutableStateFlow(false)
    val fabMenuExpanded: StateFlow<Boolean> get() = _fabMenuExpanded.asStateFlow()

    private val _cardExpanded = MutableStateFlow(false)
    val cardExpanded: StateFlow<Boolean> get() = _cardExpanded.asStateFlow()

    fun updateFabMenuExpanded(expanded: Boolean) {
        _fabMenuExpanded.value = expanded
    }

    fun updateCardExpanded(cardExpanded: Boolean) {
        _cardExpanded.value = cardExpanded
    }
}