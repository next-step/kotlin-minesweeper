package tdd.minesweeper.domain.strategy

import tdd.minesweeper.domain.AdjacentDirection
import tdd.minesweeper.domain.AdjacentMines
import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.Cells
import tdd.minesweeper.domain.ClosedCell
import tdd.minesweeper.domain.Location

class DefaultBoardCellsCreator : BoardCellsCreator {
    override fun createCells(
        area: Area,
        countOfMines: Int,
        inputManualMineLocations: Set<Location>,
    ): Cells {
        // 모든 위치 구하기
        val allLocations = createAllLocations(area)

        // 지뢰 위치 구하기
        val mineLocations = calculateMineLocations(allLocations, inputManualMineLocations, countOfMines)

        // 지뢰 심기
        val closedCells = createMinePlantedCells(allLocations, mineLocations)

        // 지뢰 인접 위치 표시
        val updatedCells = markOfAdjacentMines(closedCells, allLocations, mineLocations)

        return Cells(updatedCells)
    }

    private fun createAllLocations(area: Area): List<Location> {
        return (0 until area.height * area.width)
            .map {
                Location.from(
                    index = it,
                    width = area.width,
                )
            }
    }

    private fun calculateMineLocations(
        allLocations: List<Location>,
        inputManualMineLocations: Set<Location>,
        countOfMines: Int,
    ): Set<Location> {
        val validManualMineLocations =
            allLocations.intersect(inputManualMineLocations).take(countOfMines).toSet()

        val randomMineLocations: Set<Location> =
            (allLocations - validManualMineLocations)
                .shuffled()
                .take(countOfMines - validManualMineLocations.size)
                .toSet()

        return validManualMineLocations + randomMineLocations
    }

    private fun createMinePlantedCells(
        allLocations: List<Location>,
        mineLocations: Set<Location>,
    ): List<ClosedCell> {
        return allLocations.map { location -> ClosedCell(hasMine = (location in mineLocations)) }
    }

    private fun markOfAdjacentMines(
        closedCells: List<ClosedCell>,
        allLocations: List<Location>,
        mineLocations: Set<Location>,
    ) = closedCells
        .mapIndexed { index, cell ->
            cell.withAdjacentMines(AdjacentMines(calculateAdjacentMineCount(allLocations[index], mineLocations)))
        }

    private fun calculateAdjacentMineCount(
        location: Location,
        mineLocations: Set<Location>,
    ): Int {
        val adjacentLocations = AdjacentDirection.allAdjacentLocations(location)
        return adjacentLocations.count { it in mineLocations }
    }
}
