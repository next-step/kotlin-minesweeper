package minesweeper.domain

interface LandmineTracker {
    fun withUpdatedAdjacentMineCounts(
        cells: Cells,
        landmineLocation: Location,
    ): Cells
}
