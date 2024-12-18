package minesweeper.domain.service

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard
import minesweeper.domain.strategy.DefaultLandmineLocationSelector
import minesweeper.domain.strategy.DefaultLandmineTracker
import minesweeper.domain.strategy.LandmineLocationSelector
import minesweeper.domain.strategy.LandminePlanter
import minesweeper.domain.strategy.LandmineTracker
import minesweeper.domain.strategy.Vulture

class LandmineFieldArchitect(
    private val landmineLocationSelector: LandmineLocationSelector = DefaultLandmineLocationSelector(),
    private val landminePlanter: LandminePlanter = Vulture(),
    private val landmineTracker: LandmineTracker = DefaultLandmineTracker(),
) {
    fun design(
        board: GameBoard,
        countOfLandmines: CountOfLandmines,
    ): GameBoard {
        require(board.totalCellSize() >= countOfLandmines.value) {
            "보드의 총 셀 개수보다 지뢰 개수가 더 많습니다: countOfLandmines=${countOfLandmines.value}, totalCellSize=${board.totalCellSize()}"
        }

        val candidates = landmineLocationSelector.selectCandidates(board, countOfLandmines)

        val minePlantedCells =
            landminePlanter.plantAll(
                allCells = board.cells,
                landmineCandidates = candidates,
            )

        val result =
            candidates
                .fold(minePlantedCells) { acc, candidate ->
                    landmineTracker.withUpdatedAdjacentMineCounts(acc, candidate)
                }

        return GameBoard.from(result)
    }
}
