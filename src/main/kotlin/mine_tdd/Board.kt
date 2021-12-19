package mine_tdd

import mine_tdd.cell.Cell
import mine_tdd.cell.Cells
import mine_tdd.cell.MineCell
import mine_tdd.cell.NoneCell
import mine_tdd.cell.Position

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
        fun createBoard(width: Width, height: Height, mine: Mine = Mine()): Board {
            val count = width.value() * height.value()
            val cells = randomPosition(width, height).mapIndexed { index, position ->
                if (index < mine.value()) MineCell(position)
                else NoneCell(position)
            }.let(::Cells)
            return Board(width, height, cells)
        }

        fun randomPosition(width: Width, height: Height): List<Position> {
            return List(width.value() * height.value()) { index ->
                val x = index / width.value()
                val y = index % height.value()
                Position(x, y)
            }.shuffled()
        }
    }
}
