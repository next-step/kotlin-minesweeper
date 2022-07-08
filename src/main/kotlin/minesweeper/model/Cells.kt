package minesweeper.model

class Cells(
    val cells: MutableList<Cell>
) : MutableList<Cell> by cells {

    val mineCount
        get(): Int = cells.count { it.isMine }

    fun take(startIndex: Int, endIndex: Int): Cells = cells.subList(startIndex, endIndex).let(::Cells)

    fun mineCountIn(positions: Set<CellPosition>): Int =
        cells.count { it.isMineAndIn(positions) }

    fun findClosedCellsIn(positions: Set<CellPosition>): List<Cell> =
        cells.filter { it.isClosedAndIn(positions) }

    fun isOpenedAt(position: Position): Boolean = cells[position.position].isOpened

    fun openAt(position: Position): Cell = cells[position.position].open()

    companion object {
        fun of(positions: CellPositions, mineCellCount: Int): Cells {
            val cellPositions = positions.toList()

            val closedMines = List(mineCellCount) {
                ClosedMine(cellPositions[it])
            }

            val closedNonMineCount = positions.size - mineCellCount
            val closedNonMineCells = List(closedNonMineCount) {
                ClosedNonMine(cellPositions[mineCellCount + it])
            }

            val sortedCells = (closedMines + closedNonMineCells)
                .sortedBy { it.position }

            return Cells(sortedCells.toMutableList())
        }
    }
}
