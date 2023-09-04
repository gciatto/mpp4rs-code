package io.github.gciatto.csv

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TestCsvFiles {
    @Test
    fun testParseIris() {
        val parsedFromString = CsvStrings.iris.parseAsCSV()
        val readFromFile = parseCsvFile(CsvFiles.iris)
        assertEquals(parsedFromString, readFromFile)
    }

    @Test
    fun testParseIrisWellFormatted() {
        val parsedFromString = CsvStrings.irisWellFormatted.parseAsCSV()
        val readFromFile = parseCsvFile(CsvFiles.irisWellFormatted)
        assertEquals(parsedFromString, readFromFile)
    }

    @Test
    fun testParseIrisWithNoise() {
        val parsedFromString = CsvStrings.irisWithNoise.parseAsCSV()
        val readFromFile = parseCsvFile(CsvFiles.irisWithNoise)
        assertEquals(parsedFromString, readFromFile)
    }

    @Test
    fun testParseIrisWithWrongHeader() {
        assertFailsWith<IllegalStateException> {
            parseCsvFile(CsvFiles.irisWithWrongHeader)
        }
    }

    @Test
    fun testParseIrisWithNoHeader() {
        val parsedFromString = CsvStrings.irisWithNoHeader.parseAsCSV()
        val readFromFile = parseCsvFile(CsvFiles.irisWithNoHeader)
        assertEquals(parsedFromString, readFromFile)
    }

    @Test
    fun testParseIrisWithMisplacedHeader() {
        val parsedFromString = CsvStrings.irisWithMisplacedHeader.parseAsCSV()
        val readFromFile = parseCsvFile(CsvFiles.irisWithMisplacedHeader)
        assertEquals(parsedFromString, readFromFile)
    }
}
