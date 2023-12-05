package minesweeper.domain

data class Cell(
    val position: Position,
    val isMine: Boolean = false,
)
