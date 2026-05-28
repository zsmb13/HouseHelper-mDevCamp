package com.kotlinconf.workshop.househelper.storage

import kotlinx.browser.localStorage
import org.w3c.dom.get
import org.w3c.dom.set

actual fun persistString(key: String, value: String) {
    localStorage[key] = value
}

actual fun restoreString(key: String): String {
    return localStorage[key] ?: ""
}
