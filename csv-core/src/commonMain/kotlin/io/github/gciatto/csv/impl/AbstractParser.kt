package io.github.gciatto.csv.impl

import io.github.gciatto.csv.Configuration
import io.github.gciatto.csv.Header
import io.github.gciatto.csv.Parser
import io.github.gciatto.csv.Row
import io.github.gciatto.csv.anonymousHeader
import io.github.gciatto.csv.headerOf
import io.github.gciatto.csv.recordOf

abstract class AbstractParser(override val source: Any, override val configuration: Configuration) : Parser {

    protected open fun beforeParsing() {
        // does nothing by default
    }

    protected open fun afterParsing() {
        // does nothing by default
    }

    override fun parse(): Iterable<Row> = sequence {
        beforeParsing()
        var header: Header? = null
        var i = 0
        for (line in sourceAsLines()) {
            if (line.isBlank()) {
                continue
            } else if (configuration.isHeader(line)) {
                if (header == null) {
                    header = headerOf(configuration.getColumnNames(line))
                    yield(header)
                }
            } else if (configuration.isComment(line)) {
                continue
            } else if (configuration.isRecord(line)) {
                val fields = configuration.getFields(line)
                if (header == null) {
                    header = anonymousHeader(fields.size)
                    yield(header)
                }
                try {
                    yield(recordOf(header, fields))
                } catch (e: IllegalArgumentException) {
                    throw IllegalStateException("Invalid CSV at line $i: $line", e)
                }
            } else {
                error("Invalid CSV line at $i: $line")
            }
            i++
        }
        afterParsing()
    }.asIterable()

    protected abstract fun sourceAsLines(): Sequence<String>
}
