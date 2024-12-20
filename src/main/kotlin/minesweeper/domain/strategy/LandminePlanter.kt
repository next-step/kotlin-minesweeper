package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Location

interface LandminePlanter {
    fun plantAll(
        allCells: Cells,
        landmineCandidates: List<Location>,
    ): Cells
}
