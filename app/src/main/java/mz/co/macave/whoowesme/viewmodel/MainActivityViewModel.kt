package mz.co.macave.whoowesme.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel: ViewModel() {

    private val _fabMenuExpanded = MutableStateFlow(false)
    val fabMenuExpanded: StateFlow<Boolean> get() = _fabMenuExpanded.asStateFlow()

    fun updateFabMenuExpanded(expanded: Boolean) {
        _fabMenuExpanded.value = expanded
    }
}