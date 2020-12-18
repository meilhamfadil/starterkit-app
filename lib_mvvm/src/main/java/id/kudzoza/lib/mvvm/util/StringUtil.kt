package id.kudzoza.lib.mvvm.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

fun String.isUsername(): Boolean =
    this.matches(Regex(StringConst.VALID_USERNAME_REGEX))

fun String.isEmail(): Boolean =
    this.matches(Regex(StringConst.VALID_EMAIL_ADDRESS_REGEX))

fun String.isPassword(): Boolean =
    this.matches(Regex(StringConst.VALID_PASSWORD_REGEX))

fun String.isBaktiPassword(): Boolean =
    this.length >= 6

fun String.reformatDate(inputFormat: String, output: String? = null): String {
    return try {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = Date(df.parse(this.replace("T", " ").replace("Z", " ")).time)
        val format = if (output != null) {
            SimpleDateFormat(output, Locale("in", "ID"))
        } else {
            SimpleDateFormat.getDateTimeInstance()
        }
        format.format(date)
    } catch (e: Exception) {
        this
    }
}

fun String.fullDateToSimple(output: String? = null): String {
    return try {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = Date(df.parse(this.replace("T", " ").replace("Z", " ")).time)
        val format = if (output != null) {
            SimpleDateFormat(output, Locale("in", "ID"))
        } else {
            SimpleDateFormat.getDateTimeInstance()
        }
        format.format(date)
    } catch (e: Exception) {
        this
    }
}