package minesweeper.model

data class Cells(
    val cells: List<Cell>
) : List<Cell> by cells {

    val mineCount
        get(): Int = cells.count { it.isMine() }

    fun take(startIndex: Int, endIndex: Int): Cells = cells.subList(startIndex, endIndex).let(::Cells)

    fun mineCountIn(positions: Set<CellPosition>): Int =
        cells.count { it.isMineIn(positions) }

    fun findClosedCellsIn(positions: Set<CellPosition>): List<Cell> =
        cells.filter { it.isClosedAndIn(positions) }

    fun openAndSurroundingNonMineCells(position: Position, mineBoard: MineBoard) =
        cells[position.position].openMeAndSurroundingNonMineCells(mineBoard)

    companion object {
        fun of(positions: CellPositions, mineCellCount: Int): Cells {
            val cellPositions = positions.toList()

            val mineCells = List(mineCellCount) {
                Cell.mine(cellPositions[it])
            }

            val closeCellCount = positions.size - mineCellCount
            val closeCells = List(closeCellCount) {
                Cell.nonMine(cellPositions[mineCellCount + it])
            }

            val sortedCells = mineCells.plus(closeCells)
                .sortedBy { it.position }

            return Cells(sortedCells)
        }
    }
}
