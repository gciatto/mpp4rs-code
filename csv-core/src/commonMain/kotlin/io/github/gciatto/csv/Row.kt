package io.github.gciatto.csv

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Row : Iterable<String> {
    @JsName("getByIndex")
    operator fun get(index: Int): String

    @JsName("size")
    val size: Int
}
