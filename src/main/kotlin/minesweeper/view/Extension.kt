package minesweeper.view

import minesweeper.domain.game.CellType
import minesweeper.domain.game.Row

fun CellType.toImage() = when (this) {
    CellType.MINE -> "*"
    CellType.NONE -> "C"
}

fun List<Row>.toShow(): String = this.joinToString("\n") {
    it.toShow()
}

fun Row.toShow(): String = this.joinToString(" ") {
    it.type.toImage()
}
