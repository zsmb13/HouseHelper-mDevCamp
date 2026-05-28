package com.kotlinconf.workshop.househelper.storage

import java.io.File

val context get() = ContextHelper.currentContext!!

actual fun persistString(key: String, value: String) {
    val file = File(context.filesDir, key)
    file.writeText(value)
}

actual fun restoreString(key: String): String {
    val file = File(context.filesDir, key)
    return if (file.exists()) file.readText() else ""
}
