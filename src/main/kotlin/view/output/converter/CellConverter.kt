package view.output.converter

import domain.Cell

object CellConverter : OutputConverter<Cell> {
    override fun convert(printable: Cell): String {
        return when (printable) {
            Cell.LAND -> "C"
            Cell.MINE -> "*"
        }
    }
}
