package minesweeper.model.board

import minesweeper.model.point.Horizontal
import minesweeper.model.point.Vertical

data class BoardLimit(
    val verticalLimit: Vertical,
    val horizontalLimit: Horizontal,
) {
    fun area(): Int {
        return this.verticalLimit.value * this.horizontalLimit.value
    }
}

fun Pair<Int, Int>.toBoardLimit(): BoardLimit {
    return BoardLimit(
        Vertical(this.first),
        Horizontal(this.second)
    )
}
