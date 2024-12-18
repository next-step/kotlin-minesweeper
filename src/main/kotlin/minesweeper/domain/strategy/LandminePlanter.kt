package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Landmine
import minesweeper.domain.cell.Location

interface LandminePlanter {
    fun plant(location: Location): Landmine

    fun plantAll(
        allCells: Cells,
        landmineCandidates: List<Location>,
    ): Cells
}
