package io.github.gciatto.csv

import java.io.File

actual fun parseCsvFile(
    path: String,
    separator: Char,
    delimiter: Char,
    comment: Char
): Table = FileParser(File(path), Configuration(separator, delimiter, comment)).parse().let(::tableOf)
