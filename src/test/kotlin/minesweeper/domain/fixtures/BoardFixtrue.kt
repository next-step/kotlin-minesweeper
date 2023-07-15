package minesweeper.domain.fixtures

import minesweeper.domain.Board
import minesweeper.domain.Cells
import minesweeper.domain.Normal

val ALL_NORAML_BOARD = Board(
    cells = Cells(
        listOf(
            listOf(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
            listOf(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
            listOf(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
        ),
    ),
    minePositions = emptyList(),
)
