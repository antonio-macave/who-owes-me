package mz.co.macave.whoowesme.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: Int = 0,
    val description: String,
    val amount: Double,
    val date: Date
)