import jcsv


STRING_IRIS = """
# sepal_length, sepal_width, petal_length, petal_width, class
5.1,3.5,1.4,0.2,Iris-setosa
4.9,3.0,1.4,0.2,Iris-setosa
4.7,3.2,1.3,0.2,Iris-setosa
"""

TABLE_IRIS = jcsv.table(
    ["sepal_length", "sepal_width", "petal_length", "petal_width", "class"],
    [5.1, 3.5, 1.4, 0.2, "Iris-setosa"],
    [4.9, 3.0, 1.4, 0.2, "Iris-setosa"],
    [4.7, 3.2, 1.3, 0.2, "Iris-setosa"],
)

STRING_IRIS_WELL_FORMATTED = """
# "sepal_length", "sepal_width", "petal_length", "petal_width", "class"
"5.1", "3.5", "1.4", "0.2", "Iris-setosa"
"4.9", "3.0", "1.4", "0.2", "Iris-setosa"
"4.7", "3.2", "1.3", "0.2", "Iris-setosa"
"""

TABLE_IRIS_WELL_FORMATTED = TABLE_IRIS

STRING_IRIS_WITH_NOISE = """
# "sepal length in cm", "sepal width, in cm", petal length , petal width, class
# comment followed by empty line

# clean line
5.1,3.5,1.4,0.2,Iris-setosa
# consistently spaced line
 4.9 , 3.0 , 1.4 , 0.2 , Iris-setosa 
# inconsistently spaced line
4.7,   3.2 ,1.3 ,    0.2 ,  Iris-setosa
"""

TABLE_IRIS_WITH_NOISE = jcsv.table(
    ["sepal length in cm", "sepal width, in cm", "petal length", "petal width", "class"],
    [5.1, 3.5, 1.4, 0.2, "Iris-setosa"],
    [4.9, 3.0, 1.4, 0.2, "Iris-setosa"],
    [4.7, 3.2, 1.3, 0.2, "Iris-setosa"],
)

STRING_IRIS_WITH_WRONG_HEADER = """
# "sepal length in cm", "sepal width, in cm", petal length , petal width
5.1,3.5,1.4,0.2,Iris-setosa
"""

STRING_IRIS_WITH_NO_HEADER = """
5.1,3.5,1.4,0.2,Iris-setosa
4.9,3.0,1.4,0.2,Iris-setosa
4.7,3.2,1.3,0.2,Iris-setosa
"""

TABLE_IRIS_WITH_NO_HEADER = jcsv.table(
    jcsv.header(5),
    [5.1, 3.5, 1.4, 0.2, "Iris-setosa"],
    [4.9, 3.0, 1.4, 0.2, "Iris-setosa"],
    [4.7, 3.2, 1.3, 0.2, "Iris-setosa"],
)

STRING_IRIS_WITH_MISPLACED_HEADER = """
5.1,3.5,1.4,0.2,Iris-setosa
# sepal_length, sepal_width, petal_length, petal_width, class
4.9,3.0,1.4,0.2,Iris-setosa
4.7,3.2,1.3,0.2,Iris-setosa
"""

TABLE_IRIS_WITH_MISPLACED_HEADER = TABLE_IRIS_WITH_NO_HEADER
