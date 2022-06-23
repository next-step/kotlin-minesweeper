package minesweeper.domain.cell

import minesweeper.domain.board.MineMaker

sealed class Cell(
    val position: Position,
    val nearbyPositions: Positions,
    val state: CellState = CellState.CLOSE
)

class Cells(
    private val cells: List<Cell>
) : List<Cell> by cells {

    fun sortedByIndex() = Cells(cells.sortedBy { it.position.index })

    companion object {
        fun of(width: Int, height: Int, numberOfMines: Int, mineMaker: MineMaker): Cells {
            val mineCells = mineMaker.createMines(width, height, numberOfMines)
            val mineIndices = mineCells.map { it.position.index }
            val numberOfCells = width * height
            val emptyIndices = (0 until numberOfCells).filterNot { it in mineIndices }
            val emptyCells = createEmptyCells(emptyIndices, width, height)

            return Cells(mineCells + emptyCells)
        }

        private fun createEmptyCells(emptyIndices: List<Int>, width: Int, height: Int): List<Empty> {
            return emptyIndices.map { index ->
                val x = index % width
                val y = index / width
                val position = Position(index, x, y)
                Empty(position, position.getNearbyPositions(width, height))
            }
        }
    }
}
