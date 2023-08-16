package io.github.gciatto.csv.impl

import io.github.gciatto.csv.Header
import io.github.gciatto.csv.Record
import io.github.gciatto.csv.Row
import io.github.gciatto.csv.Table

class DefaultTable(override val header: Header, records: Iterable<Record>) : Table {
    override val records: List<Record> by lazy { records.toList() }

    override fun get(row: Int): Row = if (row == 0) header else records[row - 1]

    override val size: Int
        get() = records.size + 1

    override fun iterator(): Iterator<Row> = (sequenceOf(header) + records.asSequence()).iterator()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultTable

        if (header != other.header) return false
        if (records != other.records) return false

        return true
    }

    override fun hashCode(): Int {
        var result = header.hashCode()
        result = 31 * result + records.hashCode()
        return result
    }

    override fun toString(): String = joinToString(", ", "Table(", ")")
}
