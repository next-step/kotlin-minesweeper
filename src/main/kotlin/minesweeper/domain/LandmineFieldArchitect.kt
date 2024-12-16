package minesweeper.domain

interface LandmineFieldArchitect {
    fun design(
        board: GameBoard,
        countOfLandmines: CountOfLandmines,
    ): GameBoard
}
