package minesweeper.model

data class Cells(
    val cells: List<Cell>
) : List<Cell> by cells {
    val mineCount
        get(): Int = cells.count { it.isMine() }

    fun generateCellsSortedByPosition(): Cells = Cells(cells.sortedBy { it.position })

    fun take(startIndex: Int, endIndex: Int): Cells = cells.subList(startIndex, endIndex).let(::Cells)

    companion object {
        fun of(positions: CellPositions, mineCellCount: Int): Cells {
            val mineCells = List(mineCellCount) {
                Cell.mine(positions[it])
            }

            val closeCellCount = positions.size - mineCellCount
            val closeCells = List(closeCellCount) {
                Cell.nonMine(positions[mineCellCount + it])
            }

            return Cells(mineCells.plus(closeCells))
        }
    }
}
