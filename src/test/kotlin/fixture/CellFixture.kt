package fixture

import domain.AroundMineCount
import domain.Cell
import domain.CellState
import domain.Closed
import domain.Opened

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
