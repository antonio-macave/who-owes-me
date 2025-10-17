package mz.co.macave.whoowesme.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppBar(title: String, onOkListener: () -> Unit, onCancelListener: () -> Unit) {
    CenterAlignedTopAppBar(
        {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onCancelListener) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onOkListener) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
fun OnPrev() {
    AppBar("Criar Devedor", {}) { }
}