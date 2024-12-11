class LandmineFieldArchitect(
    private val landmineLocationSelector: LandmineLocationSelector = LandmineLocationSelector(),
    private val vulture: Vulture = Vulture(),
) {
    fun design(
        grid: List<List<Cell>>,
        countOfLandmine: Int,
    ): List<List<Cell>> {
        val candidates = landmineLocationSelector.selectCandidates(grid, countOfLandmine)
        return grid.map { gridRow ->
            gridRow.map { cell ->
                plantMineIfCellIsInCandidates(candidates, cell)
            }
        }
    }

    private fun plantMineIfCellIsInCandidates(
        candidates: List<Cell>,
        cell: Cell,
    ) = (candidates.find { it == cell }?.let { vulture.plantMine(it) } ?: cell)
}
