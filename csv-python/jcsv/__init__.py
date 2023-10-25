import typing

import jpype
import jcsv.jvm as jvm
import jcsv.python as python


_csv = jpype.JPackage("io.github.gciatto.csv")
_java = jpype.JPackage("java.lang")


Table = _csv.Table
Row = _csv.Row
Record = _csv.Record
Header = _csv.Header
Formatter = _csv.Formatter
Parser = _csv.Parser
Configuration = _csv.Configuration
Csv = _csv.Csv
CsvJvm = _csv.CsvJvm


def header(*args):
    if len(args) == 1 and isinstance(args[0], int):
        return Csv.anonymousHeader(args[0])
    return python.iterable_or_varargs(args, lambda xs: Csv.headerOf(jvm.Iterable@map(str, xs)))


def record(header, *args):
    return python.iterable_or_varargs(args, lambda xs: Csv.recordOf(header, jvm.Iterable@map(str, xs)))


def __ensure_header(h):
    return h if isinstance(h, Header) else header(h)


def __ensure_record(r, h):
    return r if isinstance(r, Record) else record(h, r)


def table(header, *args):
    header = __ensure_header(header)
    args = [__ensure_record(row, header) for row in args]
    return python.iterable_or_varargs(args, lambda xs: Csv.tableOf(header, jvm.Iterable@xs))


def parse_csv_string(string, separator = Csv.DEFAULT_SEPARATOR, delimiter = Csv.DEFAULT_DELIMITER, comment = Csv.DEFAULT_COMMENT):
    return Csv.parseAsCSV(string, separator, delimiter, comment)


def parse_csv_file(path, separator = Csv.DEFAULT_SEPARATOR, delimiter = Csv.DEFAULT_DELIMITER, comment = Csv.DEFAULT_COMMENT):
    return CsvJvm.parseCsvFile(str(path), separator, delimiter, comment)


def format_as_csv(rows, separator = Csv.DEFAULT_SEPARATOR, delimiter = Csv.DEFAULT_DELIMITER, comment = Csv.DEFAULT_COMMENT):
    return Csv.formatAsCSV(jvm.Iterable@rows, separator, delimiter, comment)


@jpype.JImplementationFor("io.github.gciatto.csv.Configuration")
class _Configuration:
    @property
    def separator(self) -> str:
        return self.getSeparator()

    @property
    def delimiter(self) -> str:
        return self.getDelimiter()

    @property
    def comment(self) -> str:
        return self.getComment()


@jpype.JImplementationFor("io.github.gciatto.csv.Row")
class _Row:
    def __len__(self):
        return self.getSize()

    def __getitem__(self, item):
        if isinstance(item, int) and item < 0:
            item = len(self) + item
        try:
            return self.get(item)
        except _java.IndexOutOfBoundsException as e:
            raise IndexError(f"index {item} out of range") from e

    @property
    def size(self):
        return len(self)


@jpype.JImplementationFor("io.github.gciatto.csv.Row")
class _Row:
    def __len__(self) -> int:
        return self.getSize()

    def __getitem__(self, item: int | str) -> str:
        if isinstance(item, int) and item < 0:
            item = len(self) + item
        try:
            return self.get(item)
        except _java.IndexOutOfBoundsException as e:
            raise IndexError(f"index {item} out of range") from e

    @property
    def size(self) -> int:
        return len(self)


@jpype.JImplementationFor("io.github.gciatto.csv.Record")
class _Record:
    @property
    def header(self) -> Header:
        return self.getHeader()

    @property
    def values(self) -> typing.List[str]:
        return [str(v) for v in self.getValues()]

    def __contains__(self, item: str) -> bool:
        return self.contains(item)


@jpype.JImplementationFor("io.github.gciatto.csv.Header")
class _Header:
    @property
    def columns(self) -> typing.List[str]:
        return [str(c) for c in self.getColumns()]

    def __contains__(self, item: str) -> bool:
        return self.contains(item)

    def index_of(self, column: str) -> int:
        return self.indexOf(column)


@jpype.JImplementationFor("io.github.gciatto.csv.Table")
class _Table:
    @property
    def header(self) -> Header:
        return self.getHeader()

    def __len__(self) -> int:
        return self.getSize()

    def __getitem__(self, item: int) -> Row:
        if isinstance(item, int) and item < 0:
            item = len(self) + item
        try:
            return self.get(item)
        except _java.IndexOutOfBoundsException as e:
            raise IndexError(f"index {item} out of range") from e

    @property
    def records(self) -> typing.List[Record]:
        return self.getRecords()

    @property
    def size(self) -> int:
        return len(self)


@jpype.JImplementationFor("io.github.gciatto.csv.Formatter")
class _Formatter:
    @property
    def source(self) -> typing.Iterable[Row]:
        return self.getSource()

    @property
    def configuration(self) -> Configuration:
        return self.getConfiguration()

    # def format(self) -> typing.Iterable[str]:
    #     return self.format()


@jpype.JImplementationFor("io.github.gciatto.csv.Parser")
class _Parser:
    @property
    def source(self) -> object:
        return self.getSource()

    @property
    def configuration(self) -> Configuration:
        return self.getConfiguration()

    # def parse(self) -> typing.Iterable[Row]:
    #     return self.parse()
