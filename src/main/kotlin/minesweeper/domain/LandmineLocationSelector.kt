package minesweeper.domain

interface LandmineLocationSelector {
    fun selectCandidates(
        board: GameBoard,
        countOfLandmines: Int,
    ): List<Location>
}
