package minesweeper.domain

data class MineCountNumber(
    val position: Position,
    val count: NearMineCount
)
