package minesweeper.domain.board

import minesweeper.domain.cell.Empty
import minesweeper.util.NearbyIndicesCalculator

class NearbyMineCounter {

    companion object {
        fun count(mineBoard: MineBoard) {
            mineBoard.mineIndices.forEach { index ->
                accNearbyMines(index, mineBoard.board)
            }
        }

        private fun accNearbyMines(index: Int, board: Board) {
            val nearbyIndices = NearbyIndicesCalculator.getNearbyIndices(index, board.width.value, board.height.value)
            nearbyIndices.forEach { nearbyIndex ->
                val cell = board.cells[nearbyIndex]
                if (cell is Empty) {
                    cell.numberOfNearbyMines += 1
                }
            }
        }
    }
}
