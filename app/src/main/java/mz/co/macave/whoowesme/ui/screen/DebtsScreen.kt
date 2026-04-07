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

@Composable
fun DebtStatus(debtStatus: Int) {

    val debtStatusText = when (debtStatus) {
        DebtStatus.PENDING.code -> stringResource(R.string.debt_status_pending)
        DebtStatus.PAID.code -> stringResource(R.string.debt_status_paid)
        DebtStatus.OVERDUE.code -> stringResource(R.string.debt_status_overdue)
        else -> ""
    }

    val debtStatusIcon = when (debtStatus) {
        DebtStatus.PENDING.code -> R.drawable.debt_status_pending
        DebtStatus.PAID.code -> R.drawable.debt_status_paid
        DebtStatus.OVERDUE.code -> R.drawable.debt_status_overdue
        else -> 0
    }

    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = ImageVector.vectorResource(id = debtStatusIcon),
            contentDescription = null
        )
        Spacer(Modifier.width(4.dp))
        Text(text = debtStatusText)
    }
}
