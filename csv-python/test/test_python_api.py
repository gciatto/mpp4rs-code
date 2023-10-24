import unittest
import test
import jcsv


table = test.TABLE_IRIS
header = table.header
records = table.records[-1]


class TestHeader(unittest.TestCase):
    def test_columns(self):
        self.assertEqual(header.columns, ["sepal_length", "sepal_width", "petal_length", "petal_width", "class"])

    def test_size(self):
        self.assertEqual(header.size, 5)

    def test_index_of(self):
        self.assertEqual(header.index_of("sepal_length"), 0)
        self.assertEqual(header.index_of("class"), 4)

    def test_contains(self):
        self.assertTrue("sepal_length" in header)

    def test_not_contains(self):
        self.assertFalse("missing_column" in header)

    def test_str(self):
        self.assertEqual(str(header), 'Header("sepal_length", "sepal_width", "petal_length", "petal_width", "class")')

    def test_eq(self):
        another_header = jcsv.header("sepal_length", "sepal_width", "petal_length", "petal_width", "class")
        self.assertEqual(header, another_header)
        self.assertEqual(hash(header), hash(another_header))

    def test_not_eq(self):
        another_header = jcsv.header("sepal_length", "sepal_width", "petal_length", "petal_width")
        self.assertNotEqual(header, another_header)
        self.assertNotEqual(hash(header), hash(another_header))

    def test_getitem(self):
        self.assertEqual(header[0], "sepal_length")
        self.assertEqual(header[4], "class")
        self.assertEqual(header[-1], "class")
        self.assertRaises(IndexError, header.__getitem__, 5)
        self.assertRaises(IndexError, header.__getitem__, -6)


class TestRecord(unittest.TestCase):
    def test_header(self):
        self.assertEqual(records.header, header)

    def test_values(self):
        self.assertEqual(records.values, ["4.7", "3.2", "1.3", "0.2", "Iris-setosa"])

    def test_contains(self):
        self.assertTrue("4.7" in records)

    def test_not_contains(self):
        self.assertFalse("missing_value" in records)

    def test_getitem(self):
        self.assertEqual(records[0], "4.7")
        self.assertEqual(records[4], "Iris-setosa")
        self.assertEqual(records[-1], "Iris-setosa")
        self.assertRaises(IndexError, records.__getitem__, 5)
        self.assertRaises(IndexError, records.__getitem__, -6)

    def test_str(self):
        self.assertEqual(str(records), 'Record("4.7", "3.2", "1.3", "0.2", "Iris-setosa")')


class TestTable(unittest.TestCase):
    def test_header(self):
        self.assertEqual(table.header, header)

    def test_size(self):
        self.assertEqual(len(table), 4)

    def test_getitem(self):
        self.assertEqual(table[0], header)
        self.assertEqual(table[1], table.records[0])
        self.assertEqual(table[2], table.records[1])
        self.assertEqual(table[3], table.records[2])
        self.assertEqual(table[-3], table.records[0])
        self.assertEqual(table[-2], table.records[1])
        self.assertEqual(table[-1], table.records[2])
        self.assertRaises(IndexError, table.__getitem__, 5)
        self.assertRaises(IndexError, table.__getitem__, -5)


if __name__ == '__main__':
    unittest.main()
