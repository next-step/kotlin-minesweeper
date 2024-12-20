package minesweeper.domain

data class GameBoardState(
    val countOfTotalCells: Int,
    val countOfClosedCells: Int,
    val countOfNumberCells: Int,
    val countOfLandmineCells: Int,
)
