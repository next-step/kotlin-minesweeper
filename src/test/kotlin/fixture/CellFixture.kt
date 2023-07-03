package fixture

import domain.Cell
import domain.CellState
import domain.Closed

fun cell(
    hasMine: Boolean = false,
    state: CellState = Closed,
) = Cell(
    hasMine = hasMine,
    state = state,
)
