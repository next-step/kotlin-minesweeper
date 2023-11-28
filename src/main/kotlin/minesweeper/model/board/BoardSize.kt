package minesweeper.model.board

import minesweeper.model.point.Horizontal
import minesweeper.model.point.Vertical

data class BoardSize(
    val verticalSize: Vertical,
    val horizontalSize: Horizontal,
)

fun Pair<Int, Int>.toBoardSize(): BoardSize {
    return BoardSize(
        Vertical(this.first),
        Horizontal(this.second)
    )
}
