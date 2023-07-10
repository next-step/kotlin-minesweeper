package minesweeper.domain.cell

import minesweeper.domain.point.Point

class Cells private constructor(private val map: MutableMap<Point, Cell> = mutableMapOf()) : Iterable<Cell> {
    private val points: Set<Point> = map.keys.toSet()

    fun at(point: Point): Cell = map.getOrDefault(point, null) ?: throw CellNotFondException(point)

    fun createMine(point: Point) {
        val cell = MineCell(point)
        add(cell)
        adjacentCells(point)
            .filterNot { it is MineCell }
            .forEach { add(it.increase()) }
    }

    fun open(point: Point): Cell {

        val cell = at(point)

        if (cell.isOpened) return cell

        cell.open()

        if (cell is ClearCell) {
            adjacentCells(point).forEach { open(it.point) }
        }

        return cell
    }

    fun notOpenedCells(): List<Cell> = map.values.filterNot { it.isOpened }

    override fun iterator(): Iterator<Cell> = map.values.sorted().iterator()

    private fun add(cell: Cell) = map.put(cell.point, cell)

    private fun adjacentCells(point: Point) = point.adjacent()
        .filter { points.contains(it) }
        .map { at(it) }

    companion object {
        fun from(collection: Collection<Cell>): Cells = Cells(collection.associateBy { it.point }.toMutableMap())
    }
}
