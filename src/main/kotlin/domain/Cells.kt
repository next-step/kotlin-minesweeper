package domain

import domain.strategy.MineCellGenerator

@JvmInline
value class Cells(private val cells: List<Cell>) {
    val height: Row
        get() = cells.maxBy { it.coordinate.row }.coordinate.row
    val width: Col
        get() = cells.maxBy { it.coordinate.col }.coordinate.col

    fun numberOfTotalCells(): Int = cells.size

    fun isAnyMineCellOpened(): Boolean {
        return cells
            .filter { it.isMineCell() }
            .any { it.isOpened() }
    }

    fun isAllEmptyCellsOpened(): Boolean {
        return cells
            .filter { it.isMineCell().not() }
            .all { it.isOpened() }
    }

    fun get(coordinate: Coordinate): Cell {
        return cells.firstOrNull { it.coordinate == coordinate }
            ?: throw NoSuchElementException("Coordinate $coordinate not found")
    }

    fun groupByRow(): Map<Row, List<Cell>> {
        return cells.groupBy { it.coordinate.row }
    }

    companion object {
        fun of(mineCellGenerator: MineCellGenerator): Cells {
            val mineCells = mineCellGenerator.execute()
            return Cells(mineCells.toList())
        }
    }
}
