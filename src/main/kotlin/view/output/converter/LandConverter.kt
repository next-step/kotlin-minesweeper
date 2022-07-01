package view.output.converter

import domain.Cell

object LandConverter : OutputConverter<Cell.Land> {
    override fun convert(printable: Cell.Land): String {
        return printable.nearMineCount.toString()
    }
}
