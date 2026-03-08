package mz.co.macave.whoowesme.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun format(date: String): String {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MMM-dd")
    val outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())

    val parsedDate = LocalDate.parse(date, inputFormat)
    return parsedDate.format(outputFormat)
}