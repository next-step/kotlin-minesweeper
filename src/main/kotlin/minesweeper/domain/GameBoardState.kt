package minesweeper.domain

data class GameBoardState(
    val countOfTotalCells: Int,
    val countOfClosedCells: Int,
    val countOfLandmineCells: Int,
    val countOfTotalLandmines: Int,
)
