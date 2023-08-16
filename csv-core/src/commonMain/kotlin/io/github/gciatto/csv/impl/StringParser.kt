package io.github.gciatto.csv.impl

import io.github.gciatto.csv.Configuration

class StringParser(override val source: String, configuration: Configuration) : AbstractParser(source, configuration) {
    override fun sourceAsLines(): Sequence<String> = source.lineSequence()
}
