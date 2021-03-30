package minesweeper.domain

internal abstract class Cell {
    abstract val hasMine: Boolean

    var aroundMineCount: Int? = null
    val covered: Boolean
        get() = aroundMineCount != null

    fun expose(cells: List<Cell>): Int {
        aroundMineCount = cells.filter(Cell::hasMine).count()
        return aroundMineCount as Int
    }
}
