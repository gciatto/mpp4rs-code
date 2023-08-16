package io.github.gciatto.csv

interface Row : Iterable<String> {
    operator fun get(index: Int): String

    val size: Int
}
