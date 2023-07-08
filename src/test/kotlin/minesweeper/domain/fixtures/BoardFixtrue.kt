package minesweeper.domain.fixtures

import minesweeper.domain.Board
import minesweeper.domain.Normal
import minesweeper.domain.cells

val ALL_NORAML_BOARD = Board(
    cells = listOf(
        cells(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
        cells(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
        cells(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
    ),
    minePositions = emptyList(),
)