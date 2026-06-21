package mz.co.macave.quemmedeve.extensions

import mz.co.macave.quemmedeve.model.DebtCardItem
import mz.co.macave.quemmedeve.util.SortOption

fun List<DebtCardItem>.sortedByOption(
    sortOption: SortOption
): List<DebtCardItem> {
    return when (sortOption) {
        SortOption.NAME -> sortedBy { it.debtorName }
        SortOption.DATE -> sortedBy { it.dueTo }
        SortOption.AMOUNT -> sortedBy { it.amount }
    }
}