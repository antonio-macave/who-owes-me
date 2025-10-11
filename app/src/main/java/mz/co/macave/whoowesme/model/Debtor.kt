package mz.co.macave.whoowesme.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debtors")
data class Debtor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val surname: String,
    val contactNumber: String,
    val debts: List<Debt>
)