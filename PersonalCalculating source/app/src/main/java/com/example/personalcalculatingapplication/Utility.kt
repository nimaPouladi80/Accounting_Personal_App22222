package com.example.personalcalculatingapplication

import android.util.Log
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


public fun getBaseDiff(firstDate: String, secondDate: String): Long {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss"
    )

    try {
        val firstDateF: Date = dateFormat.parse(firstDate)
        val secondDateF: Date = dateFormat.parse(secondDate)
        val diff: Long = firstDateF.getTime() - secondDateF.getTime()
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        Log.e(
            "Difference: ", " days: " + days
        )
        return days
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return 0
}

public fun isTodayDate(diffDate: String): Long {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss"
    )


    try {
        val firstDateF: Date = dateFormat.parse(diffDate)

        val persianCalendar = PersianCalendar()
        var currentDate = ""
        if (persianCalendar.persianDay.toString().length == 1)
            currentDate =
                persianCalendar.persianYear.toString() + "-" + (persianCalendar.persianMonth + 1) + "-0${persianCalendar.persianDay} 00:00:00"
        else
            currentDate =
                persianCalendar.persianYear.toString() + "-" + (persianCalendar.persianMonth + 1) + "-${persianCalendar.persianDay} 00:00:00"

        val secondDateF: Date = dateFormat.parse(currentDate)

        val diff: Long = secondDateF.getTime() - firstDateF.getTime()
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        Log.e(
            "Difference: ", " days: " + days
        )
        return days
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return 0
}