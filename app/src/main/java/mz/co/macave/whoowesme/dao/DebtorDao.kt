package mz.co.macave.whoowesme.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import mz.co.macave.whoowesme.model.Debtor

@Dao
interface DebtorDao {

    @Query("SELECT * FROM debtors")
    suspend fun getAllDebtors(): List<Debtor>

    @Query("SELECT * FROM debtors WHERE id IN (:debtorIds)")
    suspend fun loadAllByIds(debtorIds: IntArray): List<Debtor>

    @Query("SELECT * FROM debtors WHERE name LIKE :name AND surname LIKE :surname LIMIT 1")
    suspend fun findByName(name: String, surname: String): Debtor

    @Insert
    suspend fun insertAll(vararg debtors: Debtor)

    @Delete
    suspend fun delete(debtor: Debtor)
}