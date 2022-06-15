package minesweeper.domain.cell

sealed class Cell(
    x: Int,
    y: Int
) {
    init {
        require(x > 0 && y > 0) { "cell position must be positive." }
    }
}
