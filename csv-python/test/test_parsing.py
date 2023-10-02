import unittest
import test
import jcsv

import jpype.imports

from java.lang import IllegalStateException


class TestParsing(unittest.TestCase):
    def test_parse_iris(self):
        table = jcsv.parse_csv_string(test.STRING_IRIS)
        self.assertEqual(table, test.TABLE_IRIS)

    def test_parse_iris_with_noise(self):
        table = jcsv.parse_csv_string(test.STRING_IRIS_WITH_NOISE)
        self.assertEqual(table, test.TABLE_IRIS_WITH_NOISE)
    
    def test_parse_iris_with_wrong_header(self):
        with self.assertRaises(IllegalStateException):
            jcsv.parse_csv_string(test.STRING_IRIS_WITH_WRONG_HEADER)

    def test_parse_iris_with_no_header(self):
        table = jcsv.parse_csv_string(test.STRING_IRIS_WITH_NO_HEADER)
        self.assertEqual(table, test.TABLE_IRIS_WITH_NO_HEADER)

    def test_parse_iris_well_formatted(self):
        table = jcsv.parse_csv_string(test.STRING_IRIS_WELL_FORMATTED)
        self.assertEqual(table, test.TABLE_IRIS_WELL_FORMATTED)

    def test_parse_iris_with_misplaced_header(self):
        table = jcsv.parse_csv_string(test.STRING_IRIS_WITH_MISPLACED_HEADER)
        self.assertEqual(table, test.TABLE_IRIS_WITH_MISPLACED_HEADER)
