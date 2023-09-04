@file:Suppress("NON_EXPORTABLE_TYPE")

package io.github.gciatto.csv

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Table : Iterable<Row> {
    @JsName("header")
    val header: Header

    @JsName("records")
    val records: List<Record>

    @JsName("get")
    operator fun get(row: Int): Row

    @JsName("size")
    val size: Int
}
