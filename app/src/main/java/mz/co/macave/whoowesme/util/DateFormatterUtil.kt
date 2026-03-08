package mz.co.macave.whoowesme.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(date: String): String {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())

    val parsedDate = LocalDate.parse(date, inputFormat)
    return parsedDate.format(outputFormat)
}

fun Double.toMzn(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("pt")).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return "${numberFormat.format(this)} MZN"
}