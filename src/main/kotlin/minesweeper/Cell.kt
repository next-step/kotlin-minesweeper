package minesweeper

data class Cell(
    val height: Height,
    val width: Width,
    var isMine: Boolean = false,
)
