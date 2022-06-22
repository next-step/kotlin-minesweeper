package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Empty
import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.compareTo
import minesweeper.domain.common.plus
import minesweeper.domain.common.times

class NearbyMineCounter {

    companion object {
        fun count(mineBoard: MineBoard) {
            mineBoard.cells.mineIndices.forEach { index ->
                accNearbyMines(mineBoard, mineBoard.cells[index])
            }
        }

        private fun accNearbyMines(mineBoard: MineBoard, mineCell: Cell) {
            NearbyDirection.values().forEach { direction ->
                val nearbyX = direction.x + mineCell.position.x
                val nearbyY = direction.y + mineCell.position.y
                val nearbyIndex = nearbyY * mineBoard.width + nearbyX

                if (nearbyX.isBetweenRange(mineBoard.width) && nearbyY.isBetweenRange(mineBoard.width) && nearbyIndex < mineBoard.size) {
                    val cell = mineBoard.cells[nearbyIndex]
                    if (cell is Empty) {
                        cell.numberOfNearbyMines += 1
                    }
                }
            }
        }

        private fun Int.isBetweenRange(width: PositiveInt) = this in 0 until width.value
    }

    enum class NearbyDirection(val x: Int, val y: Int) {
        UP_LEFT(-1, 1),
        UP(0, 1),
        UP_RIGHT(1, 1),

        LEFT(-1, 0),
        RIGHT(1, 0),

        DOWN_LEFT(-1, -1),
        DOWN(0, -1),
        DOWN_RIGHT(1, -1)
    }
}
