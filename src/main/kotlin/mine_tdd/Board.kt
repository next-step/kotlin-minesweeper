package mine_tdd

import mine_tdd.cell.Cell
import mine_tdd.cell.Cells
import mine_tdd.cell.MineCell
import mine_tdd.cell.NoneCell
import mine_tdd.cell.Position
import mine_tdd.cell.Position.Companion.findNearPosition

class Board(
    val width: Width,
    val height: Height,
    val cells: Cells,
) {

    fun row(): Int = width.value()
    fun column(): Int = height.value()

    fun findCell(position: Position): Cell? = cells.findCell(position)

    fun getRowCells(row: Int): Cells = cells.getRowCells(row)
    fun getColumnCells(column: Int): Cells = cells.getColumnCells(column)

    companion object {
        private fun randomPosition(width: Width, height: Height): List<Position> {
            return List(width.value() * height.value()) { index ->
                val x = index / width.value()
                val y = index % height.value()
                Position(x, y)
            }.shuffled()
        }

        fun Position.findMineCount(mineCell: List<Cell>): Int = this.findNearPosition().count { pos -> mineCell.any { it.position == pos } }

        fun createBoard(width: Width, height: Height, mine: Mine = Mine()): Board {
            val positions = randomPosition(width, height)
            val mineCells = positions.take(mine.value()).map { MineCell(it) }
            val noneCells = positions.filterIndexed { index, _ -> index >= mine.value() }.map { NoneCell(it, it.findMineCount(mineCells)) }
            return Board(width, height, Cells(mineCells + noneCells))
        }
    }
}
