package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.CountOfLandmines
import minesweeper.domain.cell.Location

interface LandmineLocationSelector {
    fun selectCandidates(
        cells: Cells,
        countOfLandmines: CountOfLandmines,
    ): List<Location>
}
