import argparse
import jcsv
import sys


parser = argparse.ArgumentParser(description='Echoes CSV file to stdout.', prog='python -m jcsv')
parser.add_argument('path', metavar='PATH', type=str, nargs=1, help='path of the CSV file to echo')
parser.add_argument('-s', '--separator', metavar='SEP', type=str, nargs=1, default=jcsv.Csv.DEFAULT_SEPARATOR, help='CSV separator char')
parser.add_argument('-d', '--delimiter', metavar='DEL', type=str, nargs=1, default=jcsv.Csv.DEFAULT_DELIMITER, help='CSV delimiter char')
parser.add_argument('-c', '--comment', metavar='COM', type=str, nargs=1, default=jcsv.Csv.DEFAULT_COMMENT, help='CSV comment char')

if len(sys.argv) == 0:
    parser.print_help()
    sys.exit(1)

args = parser.parse_args()
path = args.path[0]
separator = args.separator
delimiter = args.delimiter
comment = args.comment

table = jcsv.parse_csv_file(path, separator, delimiter, comment)
print(jcsv.format_as_csv(table, separator, delimiter, comment))
