package mz.co.macave.whoowesme.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import mz.co.macave.whoowesme.model.Debt
import mz.co.macave.whoowesme.util.toMzn

@Composable
fun DebtItem(debt: Debt) {
    Card {
        Column {
            Text(text = debt.description)
            Text(text = debt.amount.toMzn())
            Text(text = debt.status.toString())
        }
    }
}