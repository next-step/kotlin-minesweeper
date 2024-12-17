package minesweeper.domain

interface LandmineTracker {
    fun withUpdatedAdjacentMineCounts(
        cells: List<Cell>,
        landmineLocation: Location,
    ): List<Cell>
}
