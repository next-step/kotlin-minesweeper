package minesweeper.domain

interface LandmineTracker {
    fun withUpdatedAdjacentMineCounts(
        gameBoard: GameBoard,
        landmineLocation: Location,
    ): GameBoard
}
