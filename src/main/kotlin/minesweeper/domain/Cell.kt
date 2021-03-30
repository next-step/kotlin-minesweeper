package minesweeper.domain

internal abstract class Cell {
    abstract val hasMine: Boolean
    var roundMineCount: Int? = null

    fun expose(cells: List<Cell>) {
        roundMineCount = cells.filter(Cell::hasMine).count()
    }
}
