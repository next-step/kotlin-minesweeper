package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location

class Vulture : LandminePlanter {
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

    private fun plant(location: Location): Cell = ClosedCell(location).plantMine()
}
