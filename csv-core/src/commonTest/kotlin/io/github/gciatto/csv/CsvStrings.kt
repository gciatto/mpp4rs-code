package io.github.gciatto.csv

object CsvStrings {
    val iris: String = """
    |# sepal_length, sepal_width, petal_length, petal_width, class
    |5.1,3.5,1.4,0.2,Iris-setosa
    |4.9,3.0,1.4,0.2,Iris-setosa
    |4.7,3.2,1.3,0.2,Iris-setosa
    """.trimMargin()

    val irisWellFormatted: String = """
    |# "sepal_length", "sepal_width", "petal_length", "petal_width", "class"
    |"5.1", "3.5", "1.4", "0.2", "Iris-setosa"
    |"4.9", "3.0", "1.4", "0.2", "Iris-setosa"
    |"4.7", "3.2", "1.3", "0.2", "Iris-setosa"
    """.trimMargin()

    val irisWithNoise: String = """
    |# "sepal length in cm", "sepal width, in cm", petal length , petal width, class
    |# comment followed by empty line
    |
    |# clean line
    |5.1,3.5,1.4,0.2,Iris-setosa
    |# consistently spaced line
    | 4.9 , 3.0 , 1.4 , 0.2 , Iris-setosa 
    |# inconsistently spaced line
    |4.7,   3.2 ,1.3 ,    0.2 ,  Iris-setosa
    """.trimMargin()

    val irisWithWrongHeader: String = """
    |# "sepal length in cm", "sepal width, in cm", petal length , petal width
    |5.1,3.5,1.4,0.2,Iris-setosa
    """.trimMargin()

    val irisWithNoHeader: String = """
    |5.1,3.5,1.4,0.2,Iris-setosa
    |4.9,3.0,1.4,0.2,Iris-setosa
    |4.7,3.2,1.3,0.2,Iris-setosa
    """.trimMargin()

    val irisWithMisplacedHeader: String = """
    |5.1,3.5,1.4,0.2,Iris-setosa
    |# sepal_length, sepal_width, petal_length, petal_width, class
    |4.9,3.0,1.4,0.2,Iris-setosa
    |4.7,3.2,1.3,0.2,Iris-setosa
    """.trimMargin()
}
