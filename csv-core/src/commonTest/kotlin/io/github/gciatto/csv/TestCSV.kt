package io.github.gciatto.csv

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TestCSV {
    @Test
    fun recordBasics() {
        val record = Tables.iris(Tables.irisShortHeader).records[0]
        assertEquals("5.1", record[0])
        assertEquals("5.1", record["sepal_length"])
        assertEquals("3.5", record[1])
        assertEquals("3.5", record["sepal_width"])
        assertEquals("1.4", record[2])
        assertEquals("1.4", record["petal_length"])
        assertEquals("0.2", record[3])
        assertEquals("0.2", record["petal_width"])
        assertEquals("Iris-setosa", record[4])
        assertEquals("Iris-setosa", record["class"])
        assertFailsWith<IndexOutOfBoundsException> { record[5] }
        assertFailsWith<NoSuchElementException> { record["missing"] }
    }

    @Test
    fun tableBasics() {
        val iris = Tables.iris(Tables.irisShortHeader)
        assertEquals(Tables.irisShortHeader, iris.header)
        assertEquals(4, iris.size)
        assertEquals(iris.header, iris[0])
        for (i in 1 ..< iris.size) {
            assertEquals(iris.header, (iris[i] as Record).header)
            assertEquals(iris.records[i - 1], iris[i])
        }
        assertFailsWith<IndexOutOfBoundsException> { iris[-1] }
        assertFailsWith<IndexOutOfBoundsException> { iris[iris.size + 1] }
    }

    @Test
    fun parsingFromCleanString() {
        val iris: Table = CsvStrings.iris.parseAsCSV()
        assertEquals(
            expected = Tables.iris(Tables.irisShortHeader),
            actual = iris
        )
    }

    @Test
    fun parsingFromNoisyString() {
        val iris: Table = CsvStrings.irisWithNoise.parseAsCSV()
        assertEquals(
            expected = Tables.iris(Tables.irisLongHeader),
            actual = iris
        )
    }

    @Test
    fun parsingFromStringWithNoHeader() {
        val iris: Table = CsvStrings.irisWithNoHeader.parseAsCSV()
        assertEquals(
            expected = Tables.iris(anonymousHeader(5)),
            actual = iris
        )
    }

    @Test
    fun parsingFromStringWithMisplacedHeader() {
        val iris: Table = CsvStrings.irisWithMisplacedHeader.parseAsCSV()
        assertEquals(
            expected = Tables.iris(anonymousHeader(5)),
            actual = iris
        )
    }

    @Test
    fun parsingFromStringWithWrongHeader() {
        assertFailsWith<IllegalStateException> {
            CsvStrings.irisWithWrongHeader.parseAsCSV()
        }
    }

    @Test
    fun formattingToString() {
        val iris: Table = Tables.iris(Tables.irisShortHeader)
        assertEquals(
            expected = CsvStrings.irisWellFormatted,
            actual = iris.formatAsCSV()
        )
    }

    @Test
    fun formattingAndParsingWithCustomChars() {
        val separator = ';'
        val delimiter = '\''
        val comment = '%'
        val irisString = CsvStrings.irisWellFormatted
            .replace(DEFAULT_DELIMITER, delimiter)
            .replace(DEFAULT_COMMENT, comment)
            .replace(DEFAULT_SEPARATOR, separator)
        val irisTable = Tables.iris(Tables.irisShortHeader)
        assertEquals(
            expected = irisString,
            actual = irisTable.formatAsCSV(separator, delimiter, comment)
        )
        assertEquals(
            expected = irisTable,
            actual = irisString.parseAsCSV(separator, delimiter, comment)
        )
    }
}
