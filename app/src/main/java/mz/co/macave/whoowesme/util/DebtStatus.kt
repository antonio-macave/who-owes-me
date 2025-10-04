package mz.co.macave.whoowesme.util

enum class DebtStatus(val code: Int) {

    PENDING(1),
    PARTIALLY_PAID(2),
    PAID(3),
    OVERDUE(4),
    CANCELLED(5),
    DISPUTED(6),
    FORGIVEN(7);

}