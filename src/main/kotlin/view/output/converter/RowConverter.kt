package view.output.converter

import domain.Row

object RowConverter : OutputConverter<Row> {
    override fun convert(printable: Row): String {
        return printable.value.joinToString(" ") { cell ->
            CellConverter.convert(cell)
        }
    }
}
