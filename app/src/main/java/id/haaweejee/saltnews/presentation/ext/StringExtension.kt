package id.haaweejee.saltnews.presentation.ext

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toFormattedDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy - HH:mma", Locale.getDefault())

    val date = inputFormat.parse(this)
    return outputFormat.format(date)
}
