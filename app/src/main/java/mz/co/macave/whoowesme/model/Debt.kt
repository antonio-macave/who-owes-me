package mz.co.macave.whoowesme.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Debt (
    @PrimaryKey val id: Int,
    val status: Int,
    val amount: Double,
    val numeroPrestacoes: Int,
)