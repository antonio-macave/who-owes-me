package mz.co.macave.whoowesme.model

data class Debt (
    val id: Int,
    val status: Int,
    val amount: Double,
    val numeroPrestacoes: Int,
)