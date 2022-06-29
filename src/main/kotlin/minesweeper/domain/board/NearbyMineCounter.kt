package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine

object NearbyMineCounter {

    fun count(cells: Cells) {
        val mineCells = cells.filterIsInstance<Mine>()
        mineCells.forEach { mine ->
            cells.accNearbyMine(mine)
        }
    }

    private fun Cells.accNearbyMine(mine: Mine) {
        mine.getNearbyCells().forEach { cell ->
            cell.takeIf { it is Empty }
                ?.let { (it as Empty).accNumberOfNearbyMines() }
        }
    }
}
