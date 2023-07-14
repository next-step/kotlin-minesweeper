package minesweeper2.domain

data class Position(
    val value: Int = 0,
    var isOpened: Boolean = false,
    var isVisited: Boolean = false
)
