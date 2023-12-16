package minesweeper.model.board

import minesweeper.model.point.Horizontal
import minesweeper.model.point.Vertical

data class BoardLimit(
    val verticalLimit: Vertical,
    val horizontalLimit: Horizontal,
) {
    val area: Int
        get() = this.verticalLimit.value * this.horizontalLimit.value

    fun verticalRange(): IntRange {
        return this.verticalLimit.range()
    }

    fun horizontalRange(): IntRange {
        return this.horizontalLimit.range()
    }
}

fun Pair<Int, Int>.toBoardLimit(): BoardLimit {
    return BoardLimit(
        Vertical(this.first),
        Horizontal(this.second)
    )
}
