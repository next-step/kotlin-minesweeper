package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Location

interface LandmineTracker {
    fun withUpdatedAdjacentMineCounts(
        cells: Cells,
        landmineLocation: Location,
    ): Cells
}
