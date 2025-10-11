package mz.co.macave.whoowesme.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debts")
data class Debt (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val status: Int,
    val amount: Double,
)