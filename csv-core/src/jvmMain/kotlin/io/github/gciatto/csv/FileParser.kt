package io.github.gciatto.csv

import io.github.gciatto.csv.impl.AbstractParser
import java.io.BufferedReader
import java.io.File
import kotlin.streams.asSequence

class FileParser(override val source: File, configuration: Configuration) : AbstractParser(source, configuration) {
    private lateinit var reader: BufferedReader

    override fun beforeParsing() {
        reader = source.bufferedReader()
    }

    override fun afterParsing() {
        reader.close()
    }

    override fun sourceAsLines(): Sequence<String> = reader.lines().asSequence()
}
