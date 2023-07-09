package minesweeper.domain.cell

import minesweeper.domain.point.Point

class Cells : Iterable<Cell> {
    private val map: MutableMap<Point, Cell> = mutableMapOf()

    fun add(cell: Cell) {
        map[cell.point] = cell
    }

    fun at(point: Point): Cell = map.getOrDefault(point, null) ?: throw CellNotFondException(point)

    fun createMine(point: Point) {
        val cell = MineCell(point)
        add(cell)
        point.adjacent()
            .mapNotNull { map.getOrDefault(it, null) }
            .filterNot { it is MineCell }
            .forEach { add(it.increase()) }
    }

    fun open(point: Point): Cell {

        val thisCell = at(point)

        if (thisCell !is NotOpenedCell) {
            return thisCell
        }

        val openedCell = thisCell.open()
        add(openedCell)

        return openedCell
    }

    fun notOpenedCells(): List<Cell> = map.values.filterIsInstance<NotOpenedCell>()

    fun closeAll() {
        val closedCells = map.values.map(Cell::close)
        closedCells.forEach { add(it) }
    }
    override fun iterator(): Iterator<Cell> = map.values.sorted().iterator()
}
