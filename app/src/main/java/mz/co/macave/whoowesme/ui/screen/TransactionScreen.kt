package mz.co.macave.whoowesme.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mz.co.macave.whoowesme.model.Transaction

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = transaction.date.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = transaction.amount.toString(),
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = transaction.description
            )
        }
    }
}

@Composable
fun IconAndDescription(@DrawableRes iconRes: Int, description: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(22.dp),
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = null
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 22.sp
        )
    }
}
