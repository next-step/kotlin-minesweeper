package minesweeper.domain

class Cells(private val list: List<Cell>) : List<Cell> by list {

    fun isAllBlockOpened(): Boolean = list.filterIsInstance<Cell.Block>().all(Cell::isOpened)

    fun openAllMine() {
        list.filterIsInstance<Cell.Mine>().forEach(Cell::open)
    }

    fun findCell(coordinate: Coordinate) = list.find { it.coordinate == coordinate }
}
