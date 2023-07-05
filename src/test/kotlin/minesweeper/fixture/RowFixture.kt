package minesweeper.fixture

import minesweeper.domain.Cell
import minesweeper.domain.Row

fun row(
    vararg cells: Cell,
) = Row(cells.toList())
