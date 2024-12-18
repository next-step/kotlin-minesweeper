package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Location

interface LandminePlanter {
    fun plant(location: Location): Cell

    fun plantAll(
        allCells: Cells,
        landmineCandidates: List<Location>,
    ): Cells
}
