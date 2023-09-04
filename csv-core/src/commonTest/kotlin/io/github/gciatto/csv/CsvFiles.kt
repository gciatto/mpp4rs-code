package io.github.gciatto.csv

object CsvFiles {
    val iris: String by lazy {
        createTempFile("iris.csv", CsvStrings.iris)
    }

    val irisWellFormatted: String by lazy {
        createTempFile("irisWellFormatted.csv", CsvStrings.irisWellFormatted)
    }

    val irisWithNoise: String by lazy {
        createTempFile("irisWithNoise.csv", CsvStrings.irisWithNoise)
    }

    val irisWithWrongHeader: String by lazy {
        createTempFile("irisWithWrongHeader.csv", CsvStrings.irisWithWrongHeader)
    }

    val irisWithNoHeader: String by lazy {
        createTempFile("irisWithNoHeader.csv", CsvStrings.irisWithNoHeader)
    }

    val irisWithMisplacedHeader: String by lazy {
        createTempFile("irisWithMisplacedHeader.csv", CsvStrings.irisWithMisplacedHeader)
    }
}
