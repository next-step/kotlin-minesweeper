package minesweeper.domain.cell

import minesweeper.domain.common.Position

sealed class Cell(
    private val position: Position
) {
    val x get() = position.x
    val y get() = position.y

    init {
        require(position.x >= 0 && position.y >= 0) { "cell position must be zero or positive." }
    }
}
