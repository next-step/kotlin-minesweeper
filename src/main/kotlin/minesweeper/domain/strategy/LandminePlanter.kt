package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.LandmineCell
import minesweeper.domain.cell.Location

interface LandminePlanter {
    fun plant(location: Location): LandmineCell

    fun plantAll(
        allCells: Cells,
        landmineCandidates: List<Location>,
    ): Cells
}
