package io.github.gciatto.csv

interface Header : Row {
    val columns: List<String>
    operator fun contains(column: String): Boolean

    fun indexOf(column: String): Int
}
