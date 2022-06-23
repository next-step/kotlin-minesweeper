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
}
