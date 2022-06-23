package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.cell.Positions

class NearbyMineCounter {

    companion object {
        fun count(cells: Cells) {
            val mineCells = cells.filterIsInstance<Mine>()

            mineCells.forEach { mine ->
                cells.accNearbyMine(mine.nearbyPositions)
            }
        }

        private fun Cells.accNearbyMine(nearbyPositions: Positions) {
            nearbyPositions.forEach { position ->
                this[position.index]
                    .takeIf { it is Empty }
                    ?.let { (it as Empty).accNumberOfNearbyMines() }
            }
        }
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
