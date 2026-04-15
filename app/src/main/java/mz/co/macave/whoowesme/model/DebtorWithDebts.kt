package mz.co.macave.whoowesme.model

import androidx.room.Embedded
import androidx.room.Relation

data class DebtorWithDebts(
    @Embedded val debtor: Debtor,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val debts: List<Debt>
)