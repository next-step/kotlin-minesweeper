package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location

class DefaultLandmineTracker : LandmineTracker {
    override fun withUpdatedAdjacentMineCounts(
        cells: Cells,
        landmineLocation: Location,
    ): Cells {
        // 1.지뢰 주변 8칸의 Location 목록 구하기
        val adjacentLocations = AdjacentLocationDirection.allAdjacentLocations(landmineLocation)

        // 해당 지뢰 위치 주변의 모든 BasicCell 의 인접 지뢰 갯수 업데이트
        return Cells(
            cells.map { cell ->
                if (cell.location in adjacentLocations && cell is ClosedCell) {
                    cell.withNumberOfAdjacentLandmines(cell.numberOfAdjacentLandmines.inc())
                } else {
                    cell
                }
            },
        )
    }
}
