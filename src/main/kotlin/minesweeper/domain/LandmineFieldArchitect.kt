package minesweeper.domain

interface LandmineFieldArchitect {
    fun design(
        board: GameBoard,
        countOfLandmines: Int,
    ): GameBoard
}
