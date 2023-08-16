package io.github.gciatto.csv

data class Configuration(val separator: Char, val delimiter: Char, val comment: Char) {
    private val commentPattern: Regex by lazy {
        """^\s*$comment\s*(.*)""".toRegex()
    }
    private val field: String
        get() = """$delimiter.*?$delimiter|[^$separator]*"""

    private val recordPattern: Regex by lazy {
        """\s*($field)\s*($separator|$)""".toRegex()
    }

    fun isComment(string: String): Boolean = commentPattern.matches(string)

    fun getComment(string: String): String? = commentPattern.matchEntire(string)?.groupValues?.get(1)

    fun isRecord(string: String): Boolean = recordPattern.find(string) != null

    private fun Sequence<Pair<String, String>>.takeUpToFirstEmptySeparator(): Sequence<String> = sequence {
        var emptySep = false
        for ((field, sep) in this@takeUpToFirstEmptySeparator) {
            if (separator !in sep) {
                if (emptySep) {
                    break
                } else {
                    emptySep = true
                }
            }
            yield(field)
        }
    }

    private fun String.unwrapFromDelimiter(): String =
        if (startsWith(delimiter) && endsWith(delimiter)) {
            slice(1 ..< lastIndex)
        } else {
            this.trim()
        }

    fun getFields(string: String): List<String> =
        recordPattern.findAll(string).map {
            val field = it.groupValues[1].unwrapFromDelimiter()
            val separator = it.groupValues[2]
            field to separator
        }.takeUpToFirstEmptySeparator().toList()

    fun isHeader(string: String): Boolean =
        isComment(string) && getComment(string)?.let { isRecord(it) } == true

    fun getColumnNames(string: String): List<String> =
        getComment(string)?.let { getFields(it) } ?: emptyList()
}
