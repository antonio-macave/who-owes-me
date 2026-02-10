package mz.co.macave.whoowesme.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import mz.co.macave.whoowesme.model.DebtWithTransactions
import mz.co.macave.whoowesme.model.Transaction

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions WHERE debtId = :debtId")
    suspend fun loadAllTransactionsByDebtId(debtId: Int): List<DebtWithTransactions>

    @Insert
    suspend fun insertAll(vararg transactions: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)
}