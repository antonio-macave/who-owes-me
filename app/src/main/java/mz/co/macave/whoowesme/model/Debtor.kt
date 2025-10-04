package mz.co.macave.whoowesme.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Debtor(
    @PrimaryKey val id: Int,
    val name: String,
    val surname: String,
    val contactNumber: String,
    val debts: List<Debt>
)