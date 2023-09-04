@file:Suppress("NON_EXPORTABLE_TYPE")

package io.github.gciatto.csv

import io.github.gciatto.csv.impl.DefaultFormatter
import io.github.gciatto.csv.impl.DefaultHeader
import io.github.gciatto.csv.impl.DefaultRecord
import io.github.gciatto.csv.impl.DefaultTable
import io.github.gciatto.csv.impl.StringParser
import kotlin.js.JsExport
import kotlin.js.JsName

const val DEFAULT_SEPARATOR = ','
const val DEFAULT_DELIMITER = '"'
const val DEFAULT_COMMENT = '#'

fun headerOf(columns: Iterable<String>): Header = DefaultHeader(columns)

@JsExport
fun headerOf(vararg columns: String): Header = headerOf(columns.asIterable())
fun headerOf(columns: Sequence<String>): Header = headerOf(columns.asIterable())

@JsExport
fun anonymousHeader(size: Int): Header = headerOf((0 ..< size).map { it.toString() })

fun recordOf(header: Header, columns: Iterable<String>): Record = DefaultRecord(header, columns)

@JsExport
fun recordOf(header: Header, vararg columns: String): Record = recordOf(header, columns.asIterable())
fun recordOf(header: Header, columns: Sequence<String>): Record = recordOf(header, columns.asIterable())

fun tableOf(header: Header, records: Iterable<Record>): Table = DefaultTable(header, records)

@JsExport
@JsName("table")
fun tableOf(header: Header, vararg records: Record): Table = tableOf(header, records.asIterable())
fun tableOf(header: Header, records: Sequence<Record>): Table = tableOf(header, records.asIterable())

fun tableOf(rows: Iterable<Row>): Table {
    val records = mutableListOf<Record>()
    var header: Header? = null
    for (row in rows) {
        when (row) {
            is Header -> header = row
            is Record -> records.add(row)
        }
    }
    require(header != null || records.isNotEmpty())
    return tableOf(header ?: anonymousHeader(records[0].size), records)
}

@JsExport
@JsName("tableOf")
fun tableOf(vararg rows: Row): Table = tableOf(rows.asIterable())

fun tableOf(rows: Sequence<Row>): Table = tableOf(rows.asIterable())

@JsExport
fun Iterable<Row>.formatAsCSV(
    separator: Char = DEFAULT_SEPARATOR,
    delimiter: Char = DEFAULT_DELIMITER,
    comment: Char = DEFAULT_COMMENT
): String = DefaultFormatter(this, Configuration(separator, delimiter, comment)).format().joinToString("\n")

@JsExport
fun String.parseAsCSV(
    separator: Char = DEFAULT_SEPARATOR,
    delimiter: Char = DEFAULT_DELIMITER,
    comment: Char = DEFAULT_COMMENT
): Table = StringParser(this, Configuration(separator, delimiter, comment)).parse().let(::tableOf)

expect fun parseCsvFile(
    path: String,
    separator: Char = DEFAULT_SEPARATOR,
    delimiter: Char = DEFAULT_DELIMITER,
    comment: Char = DEFAULT_COMMENT
): Table
