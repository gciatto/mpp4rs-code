package io.github.gciatto.csv

interface Record : Row {
    val header: Header

    val values: List<String>
    operator fun contains(value: String): Boolean

    operator fun get(column: String): String
}
