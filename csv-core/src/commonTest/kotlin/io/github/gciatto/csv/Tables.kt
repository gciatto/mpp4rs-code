package io.github.gciatto.csv

object Tables {
    val irisShortHeader = headerOf("sepal_length", "sepal_width", "petal_length", "petal_width", "class")

    val irisLongHeader = headerOf(
        "sepal length in cm",
        "sepal width, in cm",
        "petal length",
        "petal width",
        "class"
    )

    fun iris(header: Header): Table = tableOf(
        header,
        recordOf(header, "5.1", "3.5", "1.4", "0.2", "Iris-setosa"),
        recordOf(header, "4.9", "3.0", "1.4", "0.2", "Iris-setosa"),
        recordOf(header, "4.7", "3.2", "1.3", "0.2", "Iris-setosa")
    )
}
