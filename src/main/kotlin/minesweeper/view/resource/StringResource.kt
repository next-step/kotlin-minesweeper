package minesweeper.view.resource

import minesweeper.model.Cell

fun getString(cell: Cell?): String = when {
    cell == null -> ""
    !cell.isVisible -> "C"
    cell.isMine -> "*"
    cell is Cell.Number -> "${cell.adjustMineCount.value}"
    else -> ""
}
