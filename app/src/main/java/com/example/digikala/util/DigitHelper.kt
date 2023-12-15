package com.example.digikala.util

import java.text.DecimalFormat

object DigitHelper {
    fun digitByLocate(englishStr: String): String {
        var result = ""
        var fa = '۰'
        for (ch in englishStr) {
            fa = ch
            when (ch) {
                '0' -> fa = '۰'
                '1' -> fa = '۱'
                '2' -> fa = '۲'
                '3' -> fa = '۳'
                '4' -> fa = '۴'
                '5' -> fa = '۵'
                '6' -> fa = '۶'
                '7' -> fa = '۷'
                '8' -> fa = '۸'
                '9' -> fa = '۹'
            }
            result = "$result$fa"
        }
        return result
    }

    fun digitBySeparator(price: String): String {
        val priceFormat = DecimalFormat("###,###")
        return priceFormat.format(Integer.valueOf(price))
    }

    fun digitByLocateAndSeparator(price: String): String {
        val priceWithoutCommas = price.replace(",", "")
        val persianDigit = digitByLocate(priceWithoutCommas)
        return digitBySeparator(persianDigit)
    }

    fun applyDiscount(price: Long, discountPercent: Int): Long {
        return if (discountPercent > 0) {
            val discountAmount = price * discountPercent / 100
            price - discountAmount
        } else {
            price
        }
    }

    fun gregorianToJalali(gy: Int, gm: Int, gd: Int): String {
        val gDaysInMonth: IntArray =
            intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
        val gy2: Int = if (gm > 2) (gy + 1) else gy
        var gTotalDays: Int =
            355666 + (365 * gy) + ((gy2 + 3) / 4) - ((gy2 + 99) / 100) + ((gy2 + 399) / 400) + gd + gDaysInMonth[gm - 1]
        var jy: Int = -1595 + (33 * (gTotalDays / 12053))
        gTotalDays %= 12053
        jy += 4 * (gTotalDays / 1461)
        gTotalDays %= 1461
        if (gTotalDays > 365) {
            jy += ((gTotalDays - 1) / 365)
            gTotalDays = (gTotalDays - 1) % 365
        }
        val jm: Int
        val jd: Int
        if (gTotalDays < 186) {
            jm = 1 + (gTotalDays / 31)
            jd = 1 + (gTotalDays % 31)
        } else {
            jm = 7 + ((gTotalDays - 186) / 30)
            jd = 1 + ((gTotalDays - 186) % 30)
        }
        return "$jy/$jm/$jd"
    }

    fun jalaliToGregorian(jy: Int, jm: Int, jd: Int): String {
        val jy1: Int = jy + 1595
        var days: Int =
            -355668 + (365 * jy1) + ((jy1 / 33) * 8) + (((jy1 % 33) + 3) / 4) + jd + (if (jm < 7) ((jm - 1) * 31) else (((jm - 7) * 30) + 186))
        var gy: Int = 400 * (days / 146097)
        days %= 146097
        if (days > 36524) {
            gy += 100 * (--days / 36524)
            days %= 36524
            if (days >= 365) days++
        }
        gy += 4 * (days / 1461)
        days %= 1461
        if (days > 365) {
            gy += ((days - 1) / 365)
            days = (days - 1) % 365
        }
        var gd: Int = days + 1
        val sal_a: IntArray = intArrayOf(
            0,
            31,
            if ((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0)) 29 else 28,
            31,
            30,
            31,
            30,
            31,
            31,
            30,
            31,
            30,
            31
        )
        var gm: Int = 0
        while (gm < 13 && gd > sal_a[gm]) gd -= sal_a[gm++]
        return "$gy/$gm/$gd"
    }

}