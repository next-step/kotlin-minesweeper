package minesweeper.domain.cell

import minesweeper.domain.board.NearbyMineCounter.NearbyDirection
import minesweeper.domain.board.strategy.MineStrategy

sealed class Cell(
    val position: Position,
    val nearbyPositions: Positions
)

class Cells private constructor(
    private val cells: List<Cell>,
    val mineIndices: List<Int>
) : List<Cell> by cells {

    companion object {
        fun of(width: Int, height: Int, numberOfMines: Int, mineStrategy: MineStrategy): Cells {
            val numberOfCells = width * height
            val mineIndices = mineStrategy.getMineIndices(numberOfCells, numberOfMines)

            return createCells(width, height, mineIndices)
        }

        private fun createCells(width: Int, height: Int, mineIndices: List<Int>): Cells {
            val size = width * height
            val cells = List(size) {
                val x = it % width
                val y = it / width
                val position = Position(it, x, y)

                if (it in mineIndices) {
                    Mine(position, position.getNearbyPositions(width, height))
                } else {
                    Empty(position, position.getNearbyPositions(width, height))
                }
            }
            return Cells(cells, mineIndices)
        }

        private fun Position.getNearbyPositions(width: Int, height: Int): Positions {
            val positions = NearbyDirection.values().mapNotNull { direction ->
                val size = width * height
                val nearbyX = direction.x + this.x
                val nearbyY = direction.y + this.y
                val nearbyIndex = nearbyY * width + nearbyX

                if (nearbyX.isBetweenRange(width) && nearbyY.isBetweenRange(width) && nearbyIndex < size) {
                    Position(nearbyIndex, nearbyX, nearbyY)
                } else null
            }
            return Positions.from(positions)
        }

        private fun Int.isBetweenRange(limit: Int) = this in 0 until limit
    }
}
