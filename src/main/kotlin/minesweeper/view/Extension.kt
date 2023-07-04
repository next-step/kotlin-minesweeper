package minesweeper.view

import minesweeper.domain.game.Cell
import minesweeper.domain.game.CellType

fun CellType.toImage() = when (this) {
    CellType.MINE -> "*"
    CellType.NONE -> "C"
}

fun List<List<Cell>>.toShow(): String = this.joinToString("\n") {
    it.toRow()
}

fun List<Cell>.toRow(): String = this.joinToString(" ") {
    it.type.toImage()
}
