package minesweeper.domain

interface LandmineLocationSelector {
    fun selectCandidates(
        board: GameBoard,
        countOfLandmines: CountOfLandmines,
    ): List<Location>
}
