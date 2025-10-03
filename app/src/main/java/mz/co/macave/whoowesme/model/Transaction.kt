package mz.co.macave.whoowesme.model

import java.util.Date

data class Transaction(
    val id: Int,
    val type: Int,
    val description: String,
    val amount: Double,
    val date: Date
)