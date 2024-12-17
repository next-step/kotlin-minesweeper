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

        val allCells = board.rows.flatMap { it.cells() }

        val minePlantedCells =
            allCells
                .map { cell -> if (isLandmineLocation(candidates, cell.location())) landminePlanter.plant(cell.location()) else cell }

        var result = minePlantedCells

        candidates.forEach { candidate ->
            result = landmineTracker.withUpdatedAdjacentMineCounts(result, candidate)
        }

        return GameBoard.from(result.chunked(board.area.width))
    }

    private fun isLandmineLocation(
        candidates: List<Location>,
        location: Location,
    ): Boolean = candidates.contains(location)
}
