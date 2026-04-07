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

@Composable
fun DebtFilter(debts: List<Debt>, onClick: () -> Unit) {

    val scrollState = rememberScrollState()

    var pending by remember { mutableStateOf(false) }
    var paid by remember { mutableStateOf(false) }
    var overdue by remember { mutableStateOf(false) }

    val all = pending && paid && overdue

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .horizontalScroll(state = scrollState)
    ) {
        FilterChip(
            selected = all,
            onClick = {
                val newValue = !all
                pending = newValue
                paid = newValue
                overdue = newValue
                onClick()
            },
            label = { Text(text = stringResource(R.string.all)) },
            leadingIcon = {
                AnimatedVisibility(all) {
                    Icon(Icons.Default.Check, contentDescription = null)
                }
            }
        )

        Spacer(Modifier.width(8.dp))

        FilterChip(
            selected = pending,
            onClick = {
                pending = !pending
                onClick()
            },
            label = { Text(text = stringResource(R.string.debt_status_pending)) },
            leadingIcon = {
                AnimatedVisibility(pending) {
                    Icon(Icons.Default.Check, contentDescription = null)
                }
            }
        )

        Spacer(Modifier.width(8.dp))

        FilterChip(
            selected = paid,
            onClick = {
                paid = !paid
                onClick()
            },
            label = { Text(text = stringResource(R.string.debt_status_paid)) },
            leadingIcon = {
                AnimatedVisibility (paid) {
                    Icon(Icons.Default.Check, contentDescription = null)
                }
            }
        )

        Spacer(Modifier.width(8.dp))

        FilterChip(
            selected = overdue,
            onClick = {
                overdue = !overdue
                onClick()
            },
            label = { Text(text = stringResource(R.string.debt_status_overdue)) },
            leadingIcon = {
                AnimatedVisibility (overdue) {
                    Icon(Icons.Default.Check, contentDescription = null)
                }
            }
        )
    }
}
