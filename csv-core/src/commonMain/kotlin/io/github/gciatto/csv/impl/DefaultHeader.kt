package io.github.gciatto.csv.impl

import io.github.gciatto.csv.Header

class DefaultHeader(columns: Iterable<String>) : Header, AbstractRow(columns.toList()) {
    private val indexesByName = columns.mapIndexed { index, name -> name to index }.toMap()

    override val columns: List<String>
        get() = values

    override fun contains(column: String): Boolean = column in indexesByName.keys

    override fun indexOf(column: String): Int = indexesByName[column] ?: -1

    override fun iterator(): Iterator<String> = values.iterator()

    override fun toString(): String = toString("Header")
}
