package mz.co.macave.whoowesme.model

data class Debtor(
    val id: Int,
    val name: String,
    val surname: String,
    val contactNumber: String,
    val debts: List<Debt>
)