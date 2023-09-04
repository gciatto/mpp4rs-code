@file:Suppress("NON_EXPORTABLE_TYPE")

package io.github.gciatto.csv

@JsExport
actual fun parseCsvFile(
    path: String,
    separator: Char,
    delimiter: Char,
    comment: Char
): Table = readFileSync(path, js("{encoding: 'utf8'}")).parseAsCSV(separator, delimiter, comment)
