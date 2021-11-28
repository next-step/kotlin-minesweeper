package minesweeper.view.resource

import minesweeper.model.Cell

fun getString(cell: Cell?): String = when (cell) {
    null -> ""
    is Cell.Zero -> "C"
    is Cell.Mine -> "*"
}
