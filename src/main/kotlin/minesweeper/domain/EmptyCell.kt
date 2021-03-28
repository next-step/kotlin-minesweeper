package minesweeper.domain

internal class EmptyCell(override val display: String = "C") : Cell() {
    override val hasMine = false

    override fun expose(cells: List<Cell>): Cell {
        val count = cells.filter(Cell::hasMine).count()
        return EmptyCell(count.toString())
    }
}
