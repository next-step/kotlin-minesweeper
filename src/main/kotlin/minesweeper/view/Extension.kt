package minesweeper.view

import minesweeper.domain.game.CellType
import minesweeper.domain.game.Row

fun CellType.toImage(count: String) = when (this) {
    CellType.MINE -> "*"
    CellType.NORMAL -> count
}

fun List<Row>.toShow(): String = this.joinToString("\n") {
    it.toShow()
}

fun Row.toShow(): String = this.joinToString(" ") {
    it.type.toImage(it.count.toString())
}
