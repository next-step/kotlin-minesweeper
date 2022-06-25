package minesweeper.model

data class Cells(
    val cells: List<Cell>
) : List<Cell> by cells {
    val mineCount
        get(): Int = cells.count { it.isMine() }

    fun generateCellsSortedByPosition(): Cells = Cells(cells.sortedBy { it.position })

    fun take(startIndex: Int, endIndex: Int): Cells = cells.subList(startIndex, endIndex).let(::Cells)

    fun mineCountIn(surroundingPositions: Set<CellPosition>): Int =
        cells.count { cell -> cell.isMineIn(surroundingPositions) }

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

            return Cells(mineCells.plus(closeCells))
        }
    }
}
