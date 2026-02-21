package mz.co.macave.whoowesme.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import mz.co.macave.whoowesme.R
import mz.co.macave.whoowesme.model.Debt
import mz.co.macave.whoowesme.model.Debtor
import mz.co.macave.whoowesme.viewmodel.MainActivityViewModel


@Composable
fun DebtsList(debts: List<Debt>, debtor: Debtor) {
    LazyColumn {
        itemsIndexed(items =  debts) { index, item ->
            DebtorItem(
                debt = debts[index],
                debtor = debtor
            )
        }
    }
}

@Composable
fun DebtorSituation(balance: Double) {
    val debtorSituation = if (balance >= 0) stringResource(R.string.em_dia) else stringResource(R.string.em_atraso)
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ){
        Text(
            text = debtorSituation,
            fontSize = 14.sp
        )
    }
}


@Composable
fun Header(debtorName: String) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(24.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = debtorName.first().toString(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DebtorItem(
    viewModel: MainActivityViewModel = viewModel(),
    debt: Debt,
    debtor: Debtor
) {
    val visible by viewModel.cardExpanded.collectAsStateWithLifecycle()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { viewModel.updateCardExpanded(!visible) }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
   ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "${debtor.name} ${debtor.surname}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "${debt.status} MZN",
                        style = MaterialTheme.typography.bodyLarge,
                        //color = if (debt.amount >= 0) GoodSituation else RedSituation
                    )
                }
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                BottomButtons({}) { }
            }

        }

    }
}

@Composable
fun DebtInfo(debt: Debt) {

}

@Composable
fun BottomButtons(onPrimaryClick: () -> Unit, onSecondaryClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        OutlinedButton(
            onClick = onSecondaryClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.outline_time_24),
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(text = stringResource(R.string.history))
        }
        Spacer(Modifier.width(16.dp))
        Button(
            onClick = onPrimaryClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text(text = stringResource(R.string.transaction))
        }
    }
}

@Preview
@Composable
fun Previews() {
    //BottomButtons(onPrimaryClick = { }) { }
    val debt = Debt(
        id = 1,
        status = 0,
        description = "Car",
        additionalNotes = "5 prestações",
        amount = 20000.0,
        debtorId = 1
    )


    val debtor = Debtor(
        id = 2,
        name = "Name",
        surname = "Surname",
        contactNumber = "821234567",

    )
    DebtorItem(debt = debt, debtor = debtor)
}