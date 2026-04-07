package mz.co.macave.whoowesme.model

enum class DebtStatus(val debtStatusCode: Int) {
    PAID(1),
    UP_TO_DATE(0),
    OVERDUE(-1);
}