package minesweeper.domain.service

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard
import minesweeper.domain.strategy.DefaultLandmineLocationSelector
import minesweeper.domain.strategy.DefaultLandmineTracker
import minesweeper.domain.strategy.LandmineLocationSelector
import minesweeper.domain.strategy.LandminePlanter
import minesweeper.domain.strategy.LandmineTracker
import minesweeper.domain.strategy.Vulture

class GameBoardCreator(
    private val landmineLocationSelector: LandmineLocationSelector = DefaultLandmineLocationSelector(),
    private val landminePlanter: LandminePlanter = Vulture(),
    private val landmineTracker: LandmineTracker = DefaultLandmineTracker(),
) {
    fun design(
        height: Int,
        width: Int,
        countOfLandmines: CountOfLandmines,
    ): GameBoard {
        validateCountOfLandmines(width, height, countOfLandmines)

        val initialBoard = GameBoard.of(height = height, width = width)

        val candidates = landmineLocationSelector.selectCandidates(initialBoard.cells, countOfLandmines)

        val minePlantedCells =
            landminePlanter.plantAll(
                allCells = initialBoard.cells,
                landmineCandidates = candidates,
            )

        val resultCells =
            candidates
                .fold(minePlantedCells) { acc, candidate ->
                    landmineTracker.withUpdatedAdjacentMineCounts(acc, candidate)
                }

        return GameBoard.from(resultCells)
    }

    private fun validateCountOfLandmines(
        width: Int,
        height: Int,
        countOfLandmines: CountOfLandmines,
    ) {
        require((width * height) >= countOfLandmines.value) {
            "보드의 총 셀 개수보다 지뢰 개수가 더 많습니다: countOfLandmines=${countOfLandmines.value}, totalCellSize=${(width * height)}"
        }
    }
}