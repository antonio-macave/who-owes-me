package mz.co.macave.whoowesme.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mz.co.macave.whoowesme.R

@Composable
fun CreateDebt() {

}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DebtorSituationSelector(onOptionSelected: (String) -> Unit) {

    val options = listOf(stringResource(R.string.existing), stringResource(R.string.new_one))
    var selectedIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween)
    ) {
        val modifiers = listOf(Modifier.weight(1f), Modifier.weight(1f))
        options.forEachIndexed { index, option ->
            ToggleButton(
                modifier = modifiers[index].semantics { role = Role.RadioButton },
                checked = (index == selectedIndex),
                onCheckedChange = {
                    selectedIndex = index
                    onOptionSelected(option)
                },
                shapes = when (index) {
                    0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                    options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                    else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                }
            ) {
                if (index == selectedIndex) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(ToggleButtonDefaults.IconSpacing))
                Text(text = options[index])
            }
        }
    }
}

@Composable
fun AmountField() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
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
            label = { Text(text = stringResource(R.string.amount)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            suffix = { Text(text = "MZN") }
        )
    }
}

@Composable
fun DueDate(onDialogRequestListener: () -> Unit) {
    var date by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = onDialogRequestListener,
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        datePickerState.selectedDateMillis
                    }
                ) {
                    Text(text = stringResource(android.R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text(text = stringResource(android.R.string.cancel))
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }

    TextField(
        modifier = Modifier.width(256.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
        value = date,
        onValueChange = {
            date = it
        },
        label = { Text(text = stringResource(R.string.due_date)) },
        singleLine = true,
        trailingIcon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = null) }
    )
}

@Composable
fun NameFields() {
    Column {

        var name by remember { mutableStateOf("") }
        var surname by remember { mutableStateOf("") }

        TextField(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text(text = stringResource(R.string.name)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
        )

        TextField(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            value = surname,
            onValueChange = {
                surname = it
            },
            label = { Text(text = stringResource(R.string.surname)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )
    }
}

@Composable
fun DescriptionField() {
    var description by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        value = description,
        label = { Text(text = stringResource(R.string.description)) },
        onValueChange = { description = it },
        minLines = 3,
        maxLines = 3
    )
}

@Composable
fun AdditionalNotesField() {
    var additionalNotes by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        value = additionalNotes,
        label = { Text(text = stringResource(R.string.additional_notes)) },
        onValueChange = { additionalNotes = it },
        minLines = 4,
        maxLines = 4
    )
}

@Preview
@Composable
fun CreateDebtPreviews() {
    //AmountField()
    //NameFields()
    DueToDate() {}
}