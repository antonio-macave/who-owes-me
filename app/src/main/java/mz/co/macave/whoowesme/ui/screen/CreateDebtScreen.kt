package mz.co.macave.whoowesme.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreateDebt() {

}

@Composable
fun AmountField() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        var amountText by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { text ->
                val filteredValue = text.replace(".",",")
                amountText = filteredValue
            },
            value = amountText,
            singleLine = true,
            label = { Text(text = "Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            suffix = { Text(text = "MZN") }
        )
    }
}

@Preview
@Composable
fun CreateDebtPreviews() {
    AmountField()
}