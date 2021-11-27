package minesweeper.model

class Board(
    val width: Width,
    val height: Height
) {

    companion object {
        fun create(width: Width, height: Height): Board = Board(width, height)
    }
}
