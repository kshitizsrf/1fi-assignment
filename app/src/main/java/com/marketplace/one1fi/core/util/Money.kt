package com.marketplace.one1fi.core.util

import java.text.NumberFormat
import java.util.Locale

/** Formats a Double as an Indian-locale rupee string, e.g. 156091.0 -> "₹1,56,091". */
fun Double.asRupees(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    format.maximumFractionDigits = 0
    return format.format(this).replace("₹", "₹") // keep symbol consistent across locales/devices
}

fun Double.asRupeesPerMonth(): String = "${asRupees()}/mo"
