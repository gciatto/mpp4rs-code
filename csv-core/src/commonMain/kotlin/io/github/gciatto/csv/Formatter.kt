@file:Suppress("NON_EXPORTABLE_TYPE")

package io.github.gciatto.csv

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Formatter {
    @JsName("source")
    val source: Iterable<Row>

    @JsName("configuration")
    val configuration: Configuration

    @JsName("format")
    fun format(): Iterable<String>
}
