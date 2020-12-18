package id.kudzoza.lib.mvvm.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Apr/2020
 **/

fun Double.toRupiah(): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(this).toString()
}

fun Long.convertLongToTime(output: String? = null): String {
    val date = Date(this)
    val format = if (output != null) {
        SimpleDateFormat(output, Locale("in", "ID"))
    } else {
        SimpleDateFormat.getDateTimeInstance()
    }
    return format.format(date)
}

fun currentTimeToLong(): Long {
    return System.currentTimeMillis()
}

fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return df.parse(date).time
}