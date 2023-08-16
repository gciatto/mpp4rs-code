package io.github.gciatto.csv.impl

import io.github.gciatto.csv.Header
import io.github.gciatto.csv.Record

class DefaultRecord(
    override val header: Header,
    values: Iterable<String>
) : Record, AbstractRow(values.toList()) {

    init {
        require(header.size == super.values.size) {
            "Inconsistent amount of values (${super.values.size}) w.r.t. to header size (${header.size})"
        }
    }

    override fun contains(value: String): Boolean = values.contains(value)

    override val values: List<String>
        get() = super.values

    override fun get(column: String): String =
        header.indexOf(column).takeIf { it in 0..< size }?.let { values[it] }
            ?: throw NoSuchElementException("No such column: $column")

    override fun toString(): String = toString("Record")
}
