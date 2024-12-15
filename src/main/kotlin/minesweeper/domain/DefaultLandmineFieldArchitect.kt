package minesweeper.domain

class DefaultLandmineFieldArchitect(
    private val landmineLocationSelector: LandmineLocationSelector = DefaultLandmineLocationSelector(),
    private val landminePlanter: LandminePlanter = Vulture(),
) : LandmineFieldArchitect {
    override fun design(
        board: GameBoard,
        countOfLandmines: Int,
    ): GameBoard {
        require(board.totalCellSize() >= countOfLandmines) {
            "보드의 총 셀 개수보다 지뢰 개수가 더 많습니다: countOfLandmines=$countOfLandmines, totalCellSize=${board.totalCellSize()}"
        }
        val candidates = landmineLocationSelector.selectCandidates(board, countOfLandmines)

        return GameBoard.from(
            board.grid.map { gridRow ->
                gridRow.map { cell ->
                    plantMineIfCellIsInCandidates(candidates, cell)
                }
            },
        )
    }

    private fun plantMineIfCellIsInCandidates(
        candidates: List<Location>,
        cell: Cell,
    ) = (candidates.find { it == cell.location() }?.let { landminePlanter.plant(it) } ?: cell)
}
