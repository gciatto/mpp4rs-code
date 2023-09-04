package io.github.gciatto.csv

internal fun String.splitExtension(): Pair<String, String> {
    val splitted = split(".")
    return if (splitted.size > 1) {
        splitted.subList(0, splitted.lastIndex).joinToString(".") to splitted.last()
    } else {
        this to ""
    }
}

expect fun createTempFile(name: String, content: String): String
