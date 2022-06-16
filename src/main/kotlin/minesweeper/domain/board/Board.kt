package minesweeper.domain.board

data class Board(
    val width: Int,
    val height: Int
) {
    val size get() = width * height

    init {
        require(width > 0 && height > 0) { "width and height must be positive." }
    }
}
