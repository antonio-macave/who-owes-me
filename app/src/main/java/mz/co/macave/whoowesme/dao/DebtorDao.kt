package mz.co.macave.whoowesme.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import mz.co.macave.whoowesme.model.Debtor

@Dao
interface DebtorDao {

    @Query("SELECT * FROM debtor")
    fun getAllDebtors(): List<Debtor>

    @Query("SELECT * FROM debtor WHERE id IN (:debtorIds)")
    fun loadAllByIds(debtorIds: IntArray): List<Debtor>

    @Query("SELECT * FROM debtor WHERE name LIKE :name AND surname LIKE :surname LIMIT 1")
    fun findByName(name: String, surname: String): Debtor

    @Insert
    fun insertAll(vararg debtors: Debtor)

    @Delete
    fun delete(debtor: Debtor)
}