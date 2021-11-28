package minesweeper.view.res

import minesweeper.model.Cell

fun getString(cell: Cell?): String = when (cell) {
    null -> ""
    is Cell.Blank -> "C"
    is Cell.Mine -> "*"
}
