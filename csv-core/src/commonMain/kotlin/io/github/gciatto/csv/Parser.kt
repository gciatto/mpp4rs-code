package io.github.gciatto.csv

interface Parser {
    val source: Any
    val configuration: Configuration
    fun parse(): Iterable<Row>
}
