package fixture

import domain.Cell
import domain.Row

fun row(
    vararg cells: Cell,
) = Row(cells.toMutableList())
