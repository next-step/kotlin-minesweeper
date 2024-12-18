package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.LandmineCell
import minesweeper.domain.cell.Location

class Vulture : LandminePlanter {
    override fun plant(location: Location): LandmineCell = LandmineCell(location)

    override fun plantAll(
        allCells: Cells,
        landmineCandidates: List<Location>,
    ): Cells {
        return Cells(
            allCells.map { cell ->
                if (cell.location in landmineCandidates) {
                    plant(cell.location)
                } else {
                    cell
                }
            },
        )
    }
}
