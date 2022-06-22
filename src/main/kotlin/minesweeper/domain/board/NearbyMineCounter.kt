package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Empty

class NearbyMineCounter {

    companion object {
        fun count(cells: Cells) {
            cells.mineIndices.forEach { index ->
                accNearbyMines(cells, cells[index])
            }
        }

        private fun accNearbyMines(cells: Cells, mineCell: Cell) {
            val width = cells.last().position.x + 1

            NearbyDirection.values().forEach { direction ->
                val nearbyX = direction.x + mineCell.position.x
                val nearbyY = direction.y + mineCell.position.y
                val nearbyIndex = nearbyY * width + nearbyX

                accNearbyMine(cells, width, nearbyX, nearbyY, nearbyIndex)
            }
        }

        private fun accNearbyMine(cells: Cells, width: Int, nearbyX: Int, nearbyY: Int, nearbyIndex: Int) {
            if (nearbyX.isBetweenRange(width) && nearbyY.isBetweenRange(width) && nearbyIndex < cells.size) {
                cells[nearbyIndex]
                    .takeIf { it is Empty }
                    ?.let { (it as Empty).accNumberOfNearbyMines() }
            }
        }

        private fun Int.isBetweenRange(width: Int) = this in 0 until width
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
