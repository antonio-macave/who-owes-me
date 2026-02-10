package mz.co.macave.whoowesme.data.repository

import mz.co.macave.whoowesme.data.dao.TransactionDao
import mz.co.macave.whoowesme.model.DebtWithTransactions
import mz.co.macave.whoowesme.model.Transaction

class TransactionRepository(private val transactionDao: TransactionDao) {

    suspend fun saveTransaction(transaction: Transaction) {
        transactionDao.insertAll(transaction)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.delete(transaction)
    }

    suspend fun getTransactionsByDebtId(debtId: Int): List<DebtWithTransactions> {
        return transactionDao.loadAllTransactionsByDebtId(debtId)
    }

}