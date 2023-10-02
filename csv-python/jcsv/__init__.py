
import jpype
import jcsv.jvm as jvm
import jcsv.pyt as pyt


__csv = jpype.JPackage("io.github.gciatto.csv")


Table = __csv.Table
Row = __csv.Row
Record = __csv.Record
Header = __csv.Header
Formatter = __csv.Formatter
Parser = __csv.Parser
Csv = __csv.Csv
CsvJvm = __csv.CsvJvm


def header(*args):
    if len(args) == 1 and isinstance(args[0], int):
        return Csv.anonymousHeader(args[0])
    return pyt.iterable_or_varargs(args, lambda xs: Csv.headerOf(jvm.Iterable@map(str, xs)))


def record(header, *args):
    return pyt.iterable_or_varargs(args, lambda xs: Csv.recordOf(header, jvm.Iterable@map(str, xs)))


def __ensure_header(h):
    return h if isinstance(h, Header) else header(h)


def __ensure_record(r, h):
    return r if isinstance(r, Record) else record(h, r)


def table(header, *args):
    header = __ensure_header(header)
    args = [__ensure_record(row, header) for row in args]
    return pyt.iterable_or_varargs(args, lambda xs: Csv.tableOf(header, jvm.Iterable@xs))


def parse_csv_string(string, separator = Csv.DEFAULT_SEPARATOR, delimiter = Csv.DEFAULT_DELIMITER, comment = Csv.DEFAULT_COMMENT):
    return Csv.parseAsCSV(string, separator, delimiter, comment)


def parse_csv_file(path, separator = Csv.DEFAULT_SEPARATOR, delimiter = Csv.DEFAULT_DELIMITER, comment = Csv.DEFAULT_COMMENT):
    return CsvJvm.parseCsvFile(str(path), separator, delimiter, comment)


def format_as_csv(rows, separator = Csv.DEFAULT_SEPARATOR, delimiter = Csv.DEFAULT_DELIMITER, comment = Csv.DEFAULT_COMMENT):
    return Csv.formatAsCSV(jvm.Iterable@rows, separator, delimiter, comment)
