package minesweeper.domain

class Cells(private val list: List<Cell>) : List<Cell> by list {

    fun isAllBlockOpened(): Boolean = filterIsInstance<Cell.Block>().all(Cell::isOpened)

    fun openAllMine() {
        filterIsInstance<Cell.Mine>().forEach(Cell::open)
    }

    fun findCellOrNull(coordinate: Coordinate): Cell? = find { it.coordinate == coordinate }
}
