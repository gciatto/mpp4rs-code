@file:Suppress("NON_EXPORTABLE_TYPE")

package io.github.gciatto.csv

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Parser {
    @JsName("source")
    val source: Any

    @JsName("configuration")
    val configuration: Configuration

    @JsName("parse")
    fun parse(): Iterable<Row>
}
