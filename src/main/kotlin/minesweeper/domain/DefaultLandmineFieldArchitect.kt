package minesweeper.domain

class DefaultLandmineFieldArchitect(
    private val landmineLocationSelector: LandmineLocationSelector = DefaultLandmineLocationSelector(),
    private val landminePlanter: LandminePlanter = Vulture(),
    private val landmineTracker: LandmineTracker = DefaultLandmineTracker(),
) : LandmineFieldArchitect {
    override fun design(
        board: GameBoard,
        countOfLandmines: CountOfLandmines,
    ): GameBoard {
        require(board.totalCellSize() >= countOfLandmines.value) {
            "보드의 총 셀 개수보다 지뢰 개수가 더 많습니다: countOfLandmines=${countOfLandmines.value}, totalCellSize=${board.totalCellSize()}"
        }

        val candidates = landmineLocationSelector.selectCandidates(board, countOfLandmines)

        val minePlantedCells =
            landminePlanter.plantAll(
                allCells = board.allCells(),
                landmineCandidates = candidates,
            )

        val result =
            candidates
                .fold(minePlantedCells) { acc, candidate ->
                    landmineTracker.withUpdatedAdjacentMineCounts(acc, candidate)
                }

        return GameBoard.from(result.chunked(board.area.width))
    }
}
