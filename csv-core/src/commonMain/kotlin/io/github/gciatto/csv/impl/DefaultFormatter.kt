package io.github.gciatto.csv.impl

import io.github.gciatto.csv.Configuration
import io.github.gciatto.csv.Formatter
import io.github.gciatto.csv.Header
import io.github.gciatto.csv.Row

class DefaultFormatter(
    override val source: Iterable<Row>,
    override val configuration: Configuration
) : Formatter {
    override fun format(): Iterable<String> = source.asSequence().map(this::formatRow).asIterable()

    private fun formatAsHeader(header: Header): String = "${configuration.comment} ${formatAsRecord(header)}"

    private fun formatAsRecord(row: Row): String =
        row.joinToString("${configuration.separator} ") {
            val delimiter = configuration.delimiter
            "$delimiter$it$delimiter"
        }

    private fun formatRow(row: Row): String = when (row) {
        is Header -> formatAsHeader(row)
        else -> formatAsRecord(row)
    }
}
