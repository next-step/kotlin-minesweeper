package minesweeper.domain

data class IndexResult(val mineCount: Int, val isMine: Boolean, val isOpened: PositionStatus)
