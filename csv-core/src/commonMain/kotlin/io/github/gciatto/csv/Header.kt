@file:Suppress("NON_EXPORTABLE_TYPE")

package io.github.gciatto.csv

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Header : Row {
    @JsName("columns")
    val columns: List<String>

    @JsName("contains")
    operator fun contains(column: String): Boolean

    @JsName("indexOf")
    fun indexOf(column: String): Int
}
