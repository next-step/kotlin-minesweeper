package minesweeper.view.resource

import minesweeper.model.Cell

fun getString(cell: Cell?): String = when (cell) {
    is Cell.Zero -> "0"
    is Cell.Mine -> "*"
    is Cell.Number -> "${cell.adjustMineCount.value}"
    null -> ""
}
