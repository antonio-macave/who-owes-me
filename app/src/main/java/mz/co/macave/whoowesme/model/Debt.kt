package mz.co.macave.whoowesme.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debts")
data class Debt (
    @PrimaryKey val id: Int,
    val status: Int,
    val amount: Double,
)