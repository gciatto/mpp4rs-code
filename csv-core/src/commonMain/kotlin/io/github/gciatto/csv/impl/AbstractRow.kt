package io.github.gciatto.csv.impl

import io.github.gciatto.csv.Row

abstract class AbstractRow(protected open val values: List<String>) : Row {
    override val size: Int
        get() = values.size

    override fun get(index: Int): String = values[index]
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AbstractRow

        return values == other.values
    }

    override fun hashCode(): Int = values.hashCode()

    protected fun toString(type: String?): String {
        var prefix = ""
        var suffix = ""
        if (type != null) {
            prefix = "$type("
            suffix = ")"
        }
        return values.joinToString(", ", prefix, suffix) { "\"$it\"" }
    }

    override fun toString(): String = toString("Row")

    override fun iterator(): Iterator<String> = values.iterator()
}
