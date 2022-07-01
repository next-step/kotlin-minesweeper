package view.output.converter

import domain.Cell

object CellConverter : OutputConverter<Cell> {
    override fun convert(printable: Cell): String {
        return when (printable) {
            is Cell.Land -> LandConverter.convert(printable)
            is Cell.Mine -> "*"
        }
    }
}
