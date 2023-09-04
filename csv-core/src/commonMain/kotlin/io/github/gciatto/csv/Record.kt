@file:Suppress("NON_EXPORTABLE_TYPE")

package io.github.gciatto.csv

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Record : Row {
    @JsName("header")
    val header: Header

    @JsName("values")
    val values: List<String>

    @JsName("contains")
    operator fun contains(value: String): Boolean

    @JsName("getByName")
    operator fun get(column: String): String
}
