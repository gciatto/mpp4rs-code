package io.github.gciatto.csv

interface Formatter {
    val source: Iterable<Row>
    val configuration: Configuration
    fun format(): Iterable<String>
}
