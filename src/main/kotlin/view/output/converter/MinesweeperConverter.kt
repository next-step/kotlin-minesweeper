package view.output.converter

import domain.Minesweeper

object MinesweeperConverter : OutputConverter<Minesweeper> {
    override fun convert(printable: Minesweeper): String {
        return printable.joinToString("\n") { row ->
            RowConverter.convert(row)
        }
    }
}
