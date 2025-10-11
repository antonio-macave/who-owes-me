package mz.co.macave.whoowesme.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import mz.co.macave.whoowesme.model.Debt

@Dao
interface DebtDao {

    @Query("SELECT * FROM debts")
    suspend fun getAllDebts(): List<Debt>

    @Query("SELECT * FROM debts WHERE id IN (:debtIds)")
    suspend fun loadAllDebtsById(debtIds: IntArray): List<Debt>

    @Insert
    suspend fun insertAll(vararg debts: Debt)

    @Delete
    suspend fun delete(debt: Debt)

}