package mz.co.macave.quemmedeve.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mz.co.macave.quemmedeve.R
import mz.co.macave.quemmedeve.data.DatabaseProvider
import mz.co.macave.quemmedeve.data.repository.DebtRepository
import mz.co.macave.quemmedeve.extensions.sortedByOption
import mz.co.macave.quemmedeve.ui.activities.ui.theme.WhoOwesMeTheme
import mz.co.macave.quemmedeve.ui.screen.DebtsList
import mz.co.macave.quemmedeve.ui.screen.EmptyListScreen
import mz.co.macave.quemmedeve.ui.screen.LoadingScreen
import mz.co.macave.quemmedeve.ui.screen.SortByDialog
import mz.co.macave.quemmedeve.viewmodel.DebtsActivityViewModel
import mz.co.macave.quemmedeve.viewmodel.ViewModelFactory

class DebtsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = DatabaseProvider.getDatabase(applicationContext)
        val debtDao = db.debtDao()
        val repository = DebtRepository(debtDao)
        val factory = ViewModelFactory { DebtsActivityViewModel(repository) }
        val viewModel: DebtsActivityViewModel by viewModels { factory }

        val debtorId = intent.getIntExtra("debtorId", 0)
        val debtorName = intent.getStringExtra("debtorName")

        setContent {
            val context = LocalContext.current
            WhoOwesMeTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = debtorName ?: "")
                            },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->

                    var showSortByDialog by remember { mutableStateOf(false) }
                    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
                    val debts by viewModel.debts.collectAsStateWithLifecycle(initialValue = emptyList())
                    var sortedList by remember { mutableStateOf(debts) }
                    val sortByOption by viewModel.sortByOption.collectAsStateWithLifecycle()

                    LaunchedEffect(debts) {
                        sortedList = debts
                    }

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxHeight()
                    ) {

                        when {

                            isLoading -> LoadingScreen()
                            sortedList.isEmpty() -> EmptyListScreen(
                                description = stringResource(
                                    R.string.empty_list,
                                    stringResource(R.string.debts_lowercase)
                                )
                            )

                            else -> {

                                SortByDialog(
                                    activeOption = sortByOption,
                                    isOpen = showSortByDialog,
                                    onDismiss = { showSortByDialog = false },
                                    onConfirmation = { option ->
                                        viewModel.updateSortOption(option)
                                        sortedList = sortedList.sortedByOption(option)
                                    }
                                )

                                DebtsList(
                                    debts = debts,
                                    sortByOption = sortByOption,
                                    onSortByClicked = {
                                        showSortByDialog = true
                                    },
                                    onDebtDelete = { debt ->
                                        viewModel.deleteDebtById(debt.debtId)
                                    },
                                    onDebtClick = { debt ->
                                        val intent = Intent(context, TransactionsActivity::class.java).apply {
                                            putExtra("debtId", debt.debtId)
                                            putExtra("debtorId", debt.debtorId)
                                            putExtra("debtAmount", debt.amount)
                                            putExtra("paidAmount", debt.paidAmount)
                                        }
                                        context.startActivity(intent)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}