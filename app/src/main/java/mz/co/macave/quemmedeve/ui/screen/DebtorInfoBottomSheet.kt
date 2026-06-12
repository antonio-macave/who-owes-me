package mz.co.macave.quemmedeve.ui.screen

@Composable
fun ActionButtonRow(
    onSeeDebts: () -> Unit,
    onNewDebt: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(
            icon = Icons.AutoMirrored.Filled.List,
            text = stringResource(R.string.see_debts),
            onClick = { onSeeDebts() }
        )

        ActionButton(
            icon = Icons.Default.Add,
            text = stringResource(R.string.new_debt),
            onClick = { onNewDebt() }
        )
        ActionButton(
            icon = Icons.Default.Edit,
            text = stringResource(R.string.edit),
            onClick = { onEdit() }
        )

        ActionButton(
            icon = Icons.Default.Delete,
            text = stringResource(R.string.delete),
            onClick = { onDelete() }
        )
    }
}

@Composable
fun ActionButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(
            onClick = { onClick() }
        ) {
            Icon(icon, contentDescription = text)
        }
        Text(
            text = text,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun TotalAndPaiAmount(
    totalDebt: Double,
    paidAmount: Double,
    debtsQuantity: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.total_debt),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = totalDebt.toMzn(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.paid_amount),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = paidAmount.toMzn(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }

        Spacer(Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.debts),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "$debtsQuantity",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}