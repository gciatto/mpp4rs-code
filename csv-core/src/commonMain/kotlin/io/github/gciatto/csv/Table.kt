package io.github.gciatto.csv

interface Table : Iterable<Row> {
    val header: Header

    val records: List<Record>

    operator fun get(row: Int): Row

    val size: Int
}
