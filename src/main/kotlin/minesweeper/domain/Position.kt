package minesweeper.domain

data class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= 0) { "x position must be greater than or equal to zero" }
        require(y >= 0) { "y position must be greater than or equal to zero" }
    }
}
