package minesweeper.fixture

import minesweeper.domain.AroundMineCount
import minesweeper.domain.Cell
import minesweeper.domain.CellState
import minesweeper.domain.Closed
import minesweeper.domain.Opened

fun cell(
    state: CellState = Closed
) = Cell(
    hasMine = false,
    state = state,
)

fun cell(
    aroundMineCount: Int,
) = Cell(
    hasMine = false,
    state = Opened(AroundMineCount.of(aroundMineCount))
)

fun mine() = Cell(
    hasMine = true,
    state = Closed,
)
