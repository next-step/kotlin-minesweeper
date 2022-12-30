package minesweeper.view

import minesweeper.domain.position.Position

fun Position.toContentString(): String =
    "($row,$col)"
