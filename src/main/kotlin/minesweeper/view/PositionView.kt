package minesweeper.view

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

fun Position.toContentString(): String =
    "($row,$col)"

fun Positions.toContentString(): String =
    joinToString(", ") { it.toContentString() }
