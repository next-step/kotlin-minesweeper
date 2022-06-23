package minesweeper.model

data class Cells(
    var cells: List<Cell>
) : List<Cell> by cells {
    val mineCount
        get() = cells.count { it.isMine() }

    fun sortByPosition() {
        cells = cells.sortedBy { it.position }
    }

    fun take(startIndex: Int, endIndex: Int) = cells.subList(startIndex, endIndex).let(::Cells)

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
