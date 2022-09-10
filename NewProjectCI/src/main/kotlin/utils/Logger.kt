package utils

import java.text.SimpleDateFormat
import java.util.Calendar

class Logger {

    var enabled: Boolean = false

    fun d(message: String) {
        printLog(LogType.Debug, message)
    }

    private fun printLog(type: LogType, message: String) {
        if (!enabled) return

        val c = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd-MMM-yyyy hh-mm-ss")
        val dateTime = simpleDateFormat.format(c.time)
        print("\n[$type] $dateTime : $message")
    }

    enum class LogType {
        Debug
    }

    companion object {
        val logger = Logger()
    }
}